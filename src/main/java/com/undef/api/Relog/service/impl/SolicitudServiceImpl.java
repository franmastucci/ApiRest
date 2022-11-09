package com.undef.api.Relog.service.impl;

import com.undef.api.Relog.model.entity.Requerimiento;
import com.undef.api.Relog.model.entity.Solicitud;
import com.undef.api.Relog.model.entity.clase.Clase;
import com.undef.api.Relog.model.request.SolicitudRequest;
import com.undef.api.Relog.model.response.SolicitudDeRequerimientoResponse;
import com.undef.api.Relog.model.response.SolicitudResponse;
import com.undef.api.Relog.repository.ClaseRepository;
import com.undef.api.Relog.repository.EfectoRepository;
import com.undef.api.Relog.repository.RequerimientoRepository;
import com.undef.api.Relog.repository.SolicitudRepository;
import com.undef.api.Relog.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private RequerimientoRepository requerimientoRepository;

    @Autowired
    private EfectoRepository efectoRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;


    @Override
    public SolicitudResponse create(SolicitudRequest request) {

        var requerimiento =   requerimientoRepository.findById(request.getRequerimientoid());
        var efecto = efectoRepository.findByEfectoId(request.getEfecto());
        var clase = claseRepository.findById(efecto.getClaseId());
        var solicitud = getBuildSolicitud(request);

        requerimiento.get().getSolicitudes().add(solicitud);
        requerimientoRepository.save(requerimiento.get());

        return this.getBuildSolicitudResponse(requerimiento, clase, solicitud);
    }

    @Override
    public SolicitudDeRequerimientoResponse getSolicitud(Long id) {
        var solicitud = solicitudRepository.findById(id).get();
        return SolicitudDeRequerimientoResponse.builder()
                .id(solicitud.getId())
                .cantidad(solicitud.getCantidad())
                .efecto(solicitud.getEfecto().longValue())
                .build();

    }

    private SolicitudResponse getBuildSolicitudResponse(Optional<Requerimiento> requerimiento, Optional<Clase> clase, Solicitud solicitud) {
        return SolicitudResponse.builder()
                .clase(clase.get().getTipo()    )
                .cantidad(solicitud.getCantidad())
                .requerimientoid(requerimiento.get().getId())
                .build();
    }

    private Solicitud getBuildSolicitud(SolicitudRequest request) {
        return Solicitud.builder()
                .cantidad(request.getCantidad())
                .efecto(request.getEfecto().intValue())
                .build();
    }

}
