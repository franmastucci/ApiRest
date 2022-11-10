package com.undef.api.Relog.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EfectoRequest {

    @NotNull
    private Integer tipo;  //puede ser 1,2 0 3

    @NotNull
    @NotEmpty
    @NotBlank
    private String descripcionTipo;

    @NotNull
    private Long cantidad;

    @NotNull
    @NotEmpty
    @NotBlank
    private String unidadMedida;

    @NotNull
    private Long claseId;

}
