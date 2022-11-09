package com.undef.api.Relog.repository;

import com.undef.api.Relog.model.entity.EstadoAbastecimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoAbastecimientoRepository extends JpaRepository<EstadoAbastecimiento, Long> {

    EstadoAbastecimiento findAByAbastecimientoId(Long abastecimientoId);
    List<EstadoAbastecimiento> findAByOrganizacionId(Long organizacionid);
}
