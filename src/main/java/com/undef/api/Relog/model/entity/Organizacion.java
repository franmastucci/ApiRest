package com.undef.api.Relog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Organizacion {

    @Id @Column(name = "organizacion_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizacionId;

    @Column
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "estado_movimiento",
            joinColumns = {@JoinColumn(name = "organizacion_id")},
            inverseJoinColumns = {@JoinColumn(name = "abastecimiento_id")})
    private List<EstadoAbastecimiento> estados = new java.util.ArrayList<>();

    @OneToOne
    private Ubicacion posicion;
}


