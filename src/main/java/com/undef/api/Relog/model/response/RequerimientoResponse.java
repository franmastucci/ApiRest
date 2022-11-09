package com.undef.api.Relog.model.response;

import com.undef.api.Relog.model.entity.Solicitud;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data @Builder
public class RequerimientoResponse {

    private Long id;

    private Long organizacion;

    private LocalDateTime fechaDeEntregaRequerida;

    private List<Solicitud> solicitudes;

    private Boolean confirmado;

}
