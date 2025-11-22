package com.sisetma.backend.dto;

import lombok.Data;

@Data
public class DetalleVentaDTO {
    private Integer idProducto;
    private Integer cantidad;
    // El precio se tomará del lote en el backend para seguridad
}
