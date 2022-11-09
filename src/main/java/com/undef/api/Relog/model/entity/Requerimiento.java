package com.undef.api.Relog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Requerimiento {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long organizacion;

    @Column
    private LocalDateTime fechaDeEntregaRequerida;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "requerimiento_solicitud",
            joinColumns = {@JoinColumn(name = "requerimiento_id")},
            inverseJoinColumns = {@JoinColumn(name = "solicitud_id")})
    private List<Solicitud> solicitudes;

    @Column
    private Boolean confirmado = false;

}
