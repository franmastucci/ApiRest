package com.undef.api.Relog.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SolicitudRequest {

    @NotNull
    private Long requerimientoid;

    @NotNull
    private Long efecto;

    @NotNull
    private Long cantidad;

}
