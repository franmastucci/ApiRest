package com.undef.api.Relog.model.entity.estado.impl;


import com.undef.api.Relog.model.entity.EstadoAbastecimiento;
import com.undef.api.Relog.model.entity.estado.Estado;

public class ConStock implements Estado {

    final String CON_STOCK = "con stock";

    @Override
    public void imprimir(EstadoAbastecimiento context) {
        System.out.println("Estado: " + CON_STOCK);
    }

    @Override
    public String getEstado() {return CON_STOCK;}
}
