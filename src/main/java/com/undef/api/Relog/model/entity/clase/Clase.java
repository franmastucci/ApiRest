package com.undef.api.Relog.model.entity.clase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Builder @Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Clase  {

    @Id @Column(name = "clase_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claseId;

    @Column
    private String descripcion;

    @Column
    private String tipo;


    public  void imprimir() {};

}
