package com.undef.api.Relog.model.entity.estado;

import com.undef.api.Relog.model.entity.EstadoAbastecimiento;

import javax.persistence.Entity;
import javax.persistence.Table;

public interface Estado {

    void imprimir(EstadoAbastecimiento context);

    String getEstado();
}
