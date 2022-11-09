package com.undef.api.Relog.model.entity.movimiento.impl;

import com.undef.api.Relog.model.entity.movimiento.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingreso implements Movimiento {

    private Long cantidad;

    private String descripcion;

    @Override
    public Long realizarMovimiento(Long cantidadEfecto) {
        return cantidadEfecto + (Long) cantidad;
    }

    @Override
    public void imprimirMovimiento() {
        System.out.println("Movimiento: " + descripcion + ", Cantidad: " + cantidad);
    }
}
