package com.undef.api.Relog.model.entity.movimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "movimiento") @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class MovimientoPersistent {

    @Id @Column(name = "movimeinto_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimeintoId;

    @Column
    private Long organizacionId;

    @Column
    private Long abastecimientoId;

    @Column
    private Long tipo;

    @Column
    private Long cantidad;

    @Column
    private String descripcion;

}
