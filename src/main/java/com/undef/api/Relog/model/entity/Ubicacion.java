package com.undef.api.Relog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Builder
@Table(name = "ubicacion")
@Data @AllArgsConstructor @NoArgsConstructor
public class Ubicacion {

    @Id @Column(name = "ubicacion_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ubicacionId;

    @Column
    private BigDecimal latitud;

    @Column
    private BigDecimal longitud;

}