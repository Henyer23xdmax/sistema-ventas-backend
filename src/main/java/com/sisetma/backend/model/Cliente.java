package com.sisetma.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    private String nombre;
    private String apellidos;

    @Column(name = "nro_documento")
    private String nroDocumento;

    private String direccion;
    private String celular;
    private Boolean estado;

    @Column(name = "id_tipo_cliente")
    private Integer idTipoCliente;

    @Column(name = "id_tipo_documuento")
    private Integer idTipoDocumento;
}
