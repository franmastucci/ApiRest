package com.undef.api.Relog.repository;

import com.undef.api.Relog.model.entity.movimiento.Movimiento;
import com.undef.api.Relog.model.entity.movimiento.MovimientoPersistent;
import com.undef.api.Relog.model.response.MovimientoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoPersistent, Long> {

    @Query(value = "Select * FROM movimiento m where organizacion_id = :organizacionId", nativeQuery = true)
    List<MovimientoPersistent> findByOrganizacionId(Long organizacionId);
}
