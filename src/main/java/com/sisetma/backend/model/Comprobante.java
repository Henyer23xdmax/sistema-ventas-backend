package com.sisetma.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprobante")
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Integer idComprobante;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comprobante")
    private TipoComprobante tipoComprobante;

    private String tipo;
    private String serie;
    private Integer numero;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    private Boolean estado;

    @Column(name = "numero_comprobante")
    private String numeroComprobante;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleComprobanteProducto> detalles;
}