package com.sisetma.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compra")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "tipo_comprobante_proveedor")
    private String tipoComprobanteProveedor;

    @Column(name = "serie_proveedor")
    private String serieProveedor;

    @Column(name = "numero_proveedor")
    private String numeroProveedor;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(name = "total_pagado")
    private BigDecimal totalPagado;

    private Boolean estado;
}
