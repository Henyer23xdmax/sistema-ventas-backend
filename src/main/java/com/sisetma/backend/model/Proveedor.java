package com.sisetma.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    private String direccion;
    private String telefono;
    private String correo;
    private Boolean estado;
}
