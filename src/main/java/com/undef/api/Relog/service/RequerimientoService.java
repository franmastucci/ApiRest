package com.undef.api.Relog.service;

import com.undef.api.Relog.model.entity.Solicitud;
import com.undef.api.Relog.model.request.OrganizacionRequest;
import com.undef.api.Relog.model.request.RequerimientoRequest;
import com.undef.api.Relog.model.response.RequerimientoResponse;
import com.undef.api.Relog.model.response.SolicitudDeRequerimientoResponse;
import com.undef.api.Relog.model.response.SolicitudResponse;

import java.util.List;

public interface RequerimientoService {
    RequerimientoResponse create(RequerimientoRequest request);

    List<SolicitudDeRequerimientoResponse> getSolicitudes(Long requerimiento_id);

    String confirm(Long id);

    RequerimientoResponse getRequerimientoById(Long id);

    String agregarRequerimiento(Long requerimiento_id, Long organizacion_id);
}
