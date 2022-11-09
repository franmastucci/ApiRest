package com.undef.api.Relog.model.entity.clase;

import com.undef.api.Relog.model.entity.clase.Clase;
import lombok.Data;

@Data
public class Combustible extends Clase {

    @Override
    public void imprimir() {
        System.out.println("Clase tipo combustible");
    }
}
