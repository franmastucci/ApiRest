package com.undef.api.Relog.repository;

import com.undef.api.Relog.model.entity.estado.EstadoPersistent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<EstadoPersistent, Long> {
}
