package com.sisetma.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcategoria")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;
}
