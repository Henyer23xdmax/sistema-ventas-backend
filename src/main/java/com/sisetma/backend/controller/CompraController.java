package com.sisetma.backend.controller;

import com.sisetma.backend.model.Compra;
import com.sisetma.backend.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraRepository compraRepository;

    @GetMapping
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompra(@PathVariable Integer id) {
        return compraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = compraRepository.save(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarCompra(@PathVariable Integer id,
            @RequestBody Compra compra) {
        return compraRepository.findById(id)
                .map(compraExistente -> {
                    compraExistente.setIdProveedor(compra.getIdProveedor());
                    compraExistente.setIdUsuario(compra.getIdUsuario());
                    compraExistente.setTipoComprobanteProveedor(compra.getTipoComprobanteProveedor());
                    compraExistente.setSerieProveedor(compra.getSerieProveedor());
                    compraExistente.setNumeroProveedor(compra.getNumeroProveedor());
                    compraExistente.setFechaCompra(compra.getFechaCompra());
                    compraExistente.setTotalPagado(compra.getTotalPagado());
                    compraExistente.setEstado(compra.getEstado());
                    Compra compraActualizada = compraRepository.save(compraExistente);
                    return ResponseEntity.ok(compraActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Integer id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
