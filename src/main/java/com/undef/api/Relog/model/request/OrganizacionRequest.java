package com.undef.api.Relog.model.request;

import com.undef.api.Relog.model.entity.Ubicacion;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrganizacionRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private String nombre;

    @NotNull
    private Ubicacion pocision;

}
