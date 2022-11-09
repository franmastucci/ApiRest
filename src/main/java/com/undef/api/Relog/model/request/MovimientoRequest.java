package com.undef.api.Relog.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimientoRequest {

    private Long organizacionId;

    private Long abastecimientoId;

    private Long cantidad;

}
