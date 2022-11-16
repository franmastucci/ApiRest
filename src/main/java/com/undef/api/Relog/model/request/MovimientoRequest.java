package com.undef.api.Relog.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class MovimientoRequest {

    @NotNull
    private Long organizacionId;

    @NotNull
    private Long abastecimientoId;

    @NotNull
    private Long cantidad;

}
