package com.undef.api.Relog.model.request;



import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequerimientoRequest {

    @NotNull
    private Long organizacionId;

    @NotNull
    private Integer cantidadDiasParaEntregar;

}
