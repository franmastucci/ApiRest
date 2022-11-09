package com.undef.api.Relog.model.response;

import com.undef.api.Relog.model.entity.Ubicacion;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data @Builder
public class OrganizacionResponse {

    private Long organizacionId;

    private String nombre;

    private List<EstadoAbastecimientoResponse> estados = new java.util.ArrayList<>();

    private Ubicacion posicion;
}
