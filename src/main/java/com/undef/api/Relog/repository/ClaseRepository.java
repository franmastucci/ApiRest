package com.undef.api.Relog.repository;

import com.undef.api.Relog.model.entity.clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    Clase findByClaseId(Long claseId);
}
