package com.undef.api.Relog.repository;

import com.undef.api.Relog.model.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
    Ubicacion findByUbicacionId(Long id);

}
