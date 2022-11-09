package com.undef.api.Relog.model.response;

import com.undef.api.Relog.model.entity.clase.Clase;
import lombok.Builder;
import lombok.Data;


@Data @Builder
public class EfectoResponse {

    private Long efectoId;

    private Integer tipo;  //puede ser 1,2 0 3

    private String descripcionTipo; //puede ser "Racionamiento", "vestuario y equipo" o "Combustible y lubricante" /desayuno nombre de la clase

    private Long cantidad; //cantidad de unidades de la unidad de medida

    private String unidadMedida;  //puede ser "litros", "kilos" o "unidades"

    private Clase clase;
}
