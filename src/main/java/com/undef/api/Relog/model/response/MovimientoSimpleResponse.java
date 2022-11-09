package com.undef.api.Relog.model.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class MovimientoSimpleResponse {

    private String tipo;

    private String clase;

    private Long cantidad;

}
