package com.undef.api.Relog.model.entity.estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity(name = "estado") @Builder
public class EstadoPersistent {

    @Id @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String descripcion;
}
