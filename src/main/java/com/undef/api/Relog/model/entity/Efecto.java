package com.undef.api.Relog.model.entity;

import com.undef.api.Relog.model.entity.clase.Clase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder @Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Efecto {

    @Id @Column(name = "efecto_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long efectoId;

    @Column(nullable = false)
    private Integer tipo;  //puede ser 1,2 0 3

    @Column(name = "descripcion")
    private String descripcionTipo;

    @Column(nullable = false)
    private Long cantidad; //cantidad de unidades de la unidad de medida

    @Column(nullable = false)
    private String unidadMedida;

    @Column
    private Long claseId;

    @Column
    private Long organizacionId;

    @Transient
    private Clase clase;

}
