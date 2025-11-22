package com.sisetma.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaDTO {
    private Integer idCliente;
    private Integer idTipoComprobante; // 1: Factura, 2: Boleta
    private String serie; // Ejemplo "F001"
    private List<DetalleVentaDTO> productos;
}
