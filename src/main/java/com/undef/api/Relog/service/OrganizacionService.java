package com.undef.api.Relog.service;

import com.undef.api.Relog.model.request.OrganizacionRequest;
import com.undef.api.Relog.model.response.EfectoResponse;
import com.undef.api.Relog.model.response.MovimientoResponse;
import com.undef.api.Relog.model.response.MovimientoSimpleResponse;
import com.undef.api.Relog.model.response.OrganizacionResponse;

import java.util.List;

public interface OrganizacionService {
    OrganizacionResponse create(OrganizacionRequest request);

    List<MovimientoSimpleResponse> getMovimientos(Long organizacion_id);

    List<EfectoResponse> getEfectos(Long organizacion_id);

    OrganizacionResponse getOrganizacionById(Long organizacion_id);
}