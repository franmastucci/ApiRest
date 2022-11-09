package com.undef.api.Relog.model.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class MovimientoResponse {

    private Long cantidadActual;

    private Long tipo;

    private String descripcion;

    private Long estadoAbastecimientoActual;

}
