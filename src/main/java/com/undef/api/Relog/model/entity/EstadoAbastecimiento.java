package com.undef.api.Relog.model.entity;

import com.undef.api.Relog.model.entity.estado.Estado;
import com.undef.api.Relog.model.entity.movimiento.MovimientoPersistent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Builder
@Table(name = "estado_abastecimiento")
public class    EstadoAbastecimiento {

    @Id @Column(name = "abastecimiento_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long abastecimientoId;

    @Column
    private Long organizacionId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<MovimientoPersistent> movimientoIds;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "estado_efecto",
            joinColumns = {@JoinColumn(name = "abastecimiento_id")},
            inverseJoinColumns = {@JoinColumn(name = "efecto_id")})
    private Efecto efecto;

    @Transient
    private Estado estado;

    @Column
    private Long estadoId;

    @Column
    private Integer cantidadNecesaria;

}