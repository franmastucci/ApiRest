package com.undef.api.Relog.service.impl;

import com.undef.api.Relog.model.entity.Requerimiento;
import com.undef.api.Relog.model.entity.Solicitud;
import com.undef.api.Relog.model.request.MovimientoRequest;
import com.undef.api.Relog.model.request.RequerimientoRequest;
import com.undef.api.Relog.model.response.RequerimientoResponse;
import com.undef.api.Relog.model.response.SolicitudDeRequerimientoResponse;
import com.undef.api.Relog.repository.EstadoAbastecimientoRepository;
import com.undef.api.Relog.repository.OrganizacionRepository;
import com.undef.api.Relog.repository.RequerimientoRepository;
import com.undef.api.Relog.repository.SolicitudRepository;
import com.undef.api.Relog.service.MovimientoService;
import com.undef.api.Relog.service.RequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RequerimientoServiceImpl implements RequerimientoService {

    @Autowired
    private RequerimientoRepository requerimientoRepository;

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private EstadoAbastecimientoRepository estadoAbastecimientoRepository;

    @Autowired
    private MovimientoService movimientoService;

    @Override
    public RequerimientoResponse create(RequerimientoRequest request) {
        organizacionRepository.findById(request.getOrganizacionId()).orElseThrow(RuntimeException::new);
        var requerimiento = requerimientoRepository.save(getBuildRequerimiento(request));
        return getBuildRequerimentoResponse(requerimiento);
    }

    @Override
    public List<SolicitudDeRequerimientoResponse> getSolicitudes(Long requerimiento_id) {
        List<SolicitudDeRequerimientoResponse> responses = new ArrayList<>();
        var requerimiento = requerimientoRepository.findById(requerimiento_id);

        requerimiento.get().getSolicitudes().stream()
                .forEach(solicitud -> {
                    responses.add(this.getBuildSolicitudDeRequerimientoResponse(solicitud));
                });

        return responses;
    }

    @Override
    public String confirm(Long id) {
        var requerimiento = requerimientoRepository.findById(id);
        var organizacion = organizacionRepository.findById(requerimiento.get().getOrganizacion());
        var estados  =  estadoAbastecimientoRepository.findAByOrganizacionId(organizacion.get().getOrganizacionId());
        requerimiento.get().setConfirmado(true);

        if(!requerimiento.get().getSolicitudes().isEmpty()) {
            estados.stream()
                    .forEach(estadoAbastecimiento -> {
                        requerimiento.get().getSolicitudes().stream()
                                .filter(solicitud -> estadoAbastecimiento.getEfecto().getTipo().equals(solicitud.getEfecto()))
                                .filter(solicitud -> estadoAbastecimiento.getEstadoId().equals(1L))
                                .forEach(solicitud -> {
                                    requerimiento.get().setConfirmado(false);

                                });
                    });
        } else {
            requerimiento.get().setConfirmado(false);
        }
        requerimientoRepository.save(requerimiento.get());
        return requerimiento.get().getConfirmado().toString();
    }

    @Override
    public RequerimientoResponse getRequerimientoById(Long id) {
        return getBuildRequerimentoResponse(requerimientoRepository.findById(id).get());
    }

    @Override
    public String agregarRequerimiento(Long requerimiento_id, Long organizacion_id) {
        var estados = estadoAbastecimientoRepository.findAByOrganizacionId(organizacion_id);
        var req = requerimientoRepository.findById(requerimiento_id);

        if(req.get().getConfirmado().equals(true)) {
            estados.stream()
                    .forEach(estadoAbastecimiento -> {
                        req.get().getSolicitudes().stream()
                                .filter(solicitud -> estadoAbastecimiento.getEfecto().getTipo().equals(solicitud.getEfecto()))
                                .forEach(solicitud -> {
                                    movimientoService.generarMovimiento( MovimientoRequest.builder()
                                            .abastecimientoId(estadoAbastecimiento.getAbastecimientoId())
                                            .organizacionId(organizacion_id)
                                            .cantidad(solicitud.getCantidad()).build(),1L);
                                });
                    });
        } else {
            return "El requerimiento no se puede agregar. Antes debe ser confirmado";
        }

        return "Requerimiento agregado";
    }

    private SolicitudDeRequerimientoResponse getBuildSolicitudDeRequerimientoResponse(Solicitud solicitud) {
        return SolicitudDeRequerimientoResponse.builder()
                .id(solicitud.getId())
                .efecto(solicitud.getEfecto().longValue())
                .cantidad(solicitud.getCantidad())
                .build();
    }

    private RequerimientoResponse getBuildRequerimentoResponse(Requerimiento requerimiento) {
        return RequerimientoResponse.builder()
                .confirmado(requerimiento.getConfirmado())
                .id(requerimiento.getId())
                .organizacion(requerimiento.getOrganizacion())
                .fechaDeEntregaRequerida(requerimiento.getFechaDeEntregaRequerida())
                .solicitudes(requerimiento.getSolicitudes())
                .build();
    }

    private Requerimiento getBuildRequerimiento(RequerimientoRequest request) {
        LocalDateTime fechaDeEntrega = LocalDateTime.now().plusDays(request.getCantidadDiasParaEntregar());
        return Requerimiento.builder()
                .confirmado(false)
                .fechaDeEntregaRequerida(fechaDeEntrega)
                .organizacion(request.getOrganizacionId())
                .solicitudes(null)
                .build();
    }

}
