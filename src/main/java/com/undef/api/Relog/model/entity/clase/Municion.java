package com.undef.api.Relog.model.entity.clase;

import lombok.Data;

import javax.persistence.*;

@Data
public class Municion extends Clase {

    @Override
    public void imprimir() {
        System.out.println("Clase tipo municion");
    }

}
