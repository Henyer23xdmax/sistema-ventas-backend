package com.sisetma.backend.service;

import com.sisetma.backend.dto.DetalleVentaDTO;
import com.sisetma.backend.dto.VentaDTO;
import com.sisetma.backend.model.*;
import com.sisetma.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final ComprobanteRepository comprobanteRepository;
    private final ProductoRepository productoRepository;
    private final LoteRepository loteRepository;
    private final ClienteRepository clienteRepository;
    private final TipoComprobanteRepository tipoComprobanteRepository;

    @Transactional
    public Comprobante guardarVenta(VentaDTO ventaDto) {
        // 1. Validar Cliente y Tipo
        Cliente cliente = clienteRepository.findById(ventaDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        TipoComprobante tipo = tipoComprobanteRepository.findById(ventaDto.getIdTipoComprobante())
                .orElseThrow(() -> new RuntimeException("Tipo Comprobante no encontrado"));

        // 2. Calcular correlativo (Simple)
        Comprobante ultimo = comprobanteRepository.findTopBySerieOrderByNumeroDesc(ventaDto.getSerie());
        int nuevoNumero = (ultimo != null) ? ultimo.getNumero() + 1 : 1;
        String numeroComprobante = ventaDto.getSerie() + "-" + String.format("%08d", nuevoNumero);

        // 3. Crear cabecera
        Comprobante venta = Comprobante.builder()
                .cliente(cliente)
                .tipoComprobante(tipo)
                .tipo(tipo.getNombre())
                .serie(ventaDto.getSerie())
                .numero(nuevoNumero)
                .numeroComprobante(numeroComprobante)
                .fechaEmision(LocalDateTime.now())
                .estado(true)
                .detalles(new ArrayList<>())
                .build();

        // 4. Procesar Detalles con lógica FIFO
        for (DetalleVentaDTO item : ventaDto.getProductos()) {
            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getIdProducto()));

            int cantidadRequerida = item.getCantidad();

            // Buscar lotes disponibles ordenados por fecha vencimiento (FIFO)
            List<Lote> lotesDisponibles = loteRepository
                    .findByProductoAndCantidadGreaterThanOrderByFechaVencimientoAsc(producto, 0);

            if (lotesDisponibles.isEmpty()) {
                throw new RuntimeException("No hay stock para el producto: " + producto.getNombre());
            }

            // Iterar lotes para cubrir la demanda
            for (Lote lote : lotesDisponibles) {
                if (cantidadRequerida <= 0)
                    break;

                int cantidadAUsar = Math.min(cantidadRequerida, lote.getCantidad());

                // Crear detalle por cada lote afectado
                DetalleComprobanteProducto detalle = DetalleComprobanteProducto.builder()
                        .comprobante(venta)
                        .producto(producto)
                        .codigoLote(lote.getCodigoLote())
                        .cantidad(cantidadAUsar)
                        .precioUnitario(lote.getPrecioVenta()) // Precio del lote actual
                        .subtotal(lote.getPrecioVenta().multiply(new BigDecimal(cantidadAUsar)))
                        .build();

                venta.getDetalles().add(detalle);

                // Actualizar stock del lote
                lote.setCantidad(lote.getCantidad() - cantidadAUsar);
                loteRepository.save(lote);

                cantidadRequerida -= cantidadAUsar;
            }

            // Si después de recorrer los lotes aún falta cantidad, error
            if (cantidadRequerida > 0) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre()
                        + ". Faltan: " + cantidadRequerida);
            }
        }

        return comprobanteRepository.save(venta);
    }
}
