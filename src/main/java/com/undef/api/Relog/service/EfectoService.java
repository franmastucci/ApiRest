package com.undef.api.Relog.service;

import com.undef.api.Relog.model.entity.clase.Clase;
import com.undef.api.Relog.model.request.EfectoRequest;
import com.undef.api.Relog.model.response.EfectoResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface EfectoService {
    EfectoResponse create(EfectoRequest request);

    Clase seleccionarClase(Optional<Clase> claseFromRepo);
}
