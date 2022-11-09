package com.undef.api.Relog.model.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class SolicitudResponse {

    private Long requerimientoid;

    private Long cantidad;

    private String clase;

}
