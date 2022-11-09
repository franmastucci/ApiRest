package com.undef.api.Relog.model.entity.estado.impl;


import com.undef.api.Relog.model.entity.EstadoAbastecimiento;
import com.undef.api.Relog.model.entity.estado.Estado;

public class ConFaltantes implements Estado {

    final String CON_FALTANTES = "con faltantes";

    @Override
    public void imprimir(EstadoAbastecimiento context) {
        System.out.println("Estado: " + CON_FALTANTES);
    }

    @Override
    public String getEstado() {return CON_FALTANTES;}
}
