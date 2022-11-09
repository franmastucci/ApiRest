package com.undef.api.Relog.service;

import com.undef.api.Relog.model.request.SolicitudRequest;
import com.undef.api.Relog.model.response.SolicitudDeRequerimientoResponse;
import com.undef.api.Relog.model.response.SolicitudResponse;

public interface SolicitudService {
    SolicitudResponse create(SolicitudRequest request);

    SolicitudDeRequerimientoResponse getSolicitud(Long id);
}
