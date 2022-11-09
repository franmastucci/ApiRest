package com.undef.api.Relog.model.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class SolicitudDeRequerimientoResponse {

    private Long id;

    private Long cantidad;

    private Long efecto;

}
