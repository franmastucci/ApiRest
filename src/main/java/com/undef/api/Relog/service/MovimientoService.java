package com.undef.api.Relog.service;

import com.undef.api.Relog.model.request.MovimientoRequest;
import com.undef.api.Relog.model.response.MovimientoResponse;

public interface MovimientoService {

    MovimientoResponse generarMovimiento(MovimientoRequest request , Long tipo);
}
