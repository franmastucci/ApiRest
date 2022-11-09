package com.undef.api.Relog.service.impl;

import com.undef.api.Relog.model.entity.Efecto;
import com.undef.api.Relog.model.entity.EstadoAbastecimiento;
import com.undef.api.Relog.model.entity.Organizacion;
import com.undef.api.Relog.model.entity.Ubicacion;
import com.undef.api.Relog.model.entity.clase.Clase;
import com.undef.api.Relog.model.request.OrganizacionRequest;

import com.undef.api.Relog.model.response.*;
import com.undef.api.Relog.repository.*;
import com.undef.api.Relog.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {

    @Autowired
    OrganizacionRepository organizacionRepository;
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Autowired
    ClaseRepository claseRepository;
    @Autowired
    EfectoRepository efectoRepository;
    @Autowired
    EstadoAbastecimientoRepository estadoAbastecimientoRepository;
    @Autowired
    MovimientoRepository movimientoRepository;

    @Override
    public OrganizacionResponse create(OrganizacionRequest request) {
       var org = this.saveOrganizacion(request);
       return this.getBuildOrganizacionresponse(org);
    }

    @Override
    public List<MovimientoSimpleResponse> getMovimientos(Long organizacion_id) {
        var list = new ArrayList<MovimientoSimpleResponse>();
        movimientoRepository.findByOrganizacionId(organizacion_id).stream()
                .forEach(movimientoPersistent -> {
                    var estado = estadoAbastecimientoRepository.findAByAbastecimientoId(movimientoPersistent.getAbastecimientoId());
                    var efecto = estado.getEfecto();
                    var clase = claseRepository.getById(efecto.getClaseId());
                    list.add(MovimientoSimpleResponse.builder()
                            .clase(clase.getTipo())
                            .tipo(movimientoPersistent.getDescripcion())
                            .cantidad(movimientoPersistent.getCantidad())
                            .build());
                });
        return list;
    }

    @Override
    public List<EfectoResponse> getEfectos(Long organizacion_id) {
        List<EfectoResponse> list = new ArrayList<>();
        efectoRepository.findByOrganizacionId(organizacion_id).stream()
                .forEach(efecto -> {
                    list.add(EfectoResponse.builder()
                            .efectoId(efecto.getEfectoId())
                            .tipo(efecto.getTipo())
                            .unidadMedida(efecto.getUnidadMedida())
                            .cantidad(efecto.getCantidad())
                            .descripcionTipo(efecto.getDescripcionTipo())
                            .clase(claseRepository.findById(efecto.getClaseId()).get())
                            .build());
                });

        return list;
    }

    @Override
    public OrganizacionResponse getOrganizacionById(Long organizacion_id) {
        var org = organizacionRepository.getReferenceById(organizacion_id);
        var estados = estadoAbastecimientoRepository.findAByOrganizacionId(organizacion_id);
        List<EstadoAbastecimientoResponse> estadosResponse = new ArrayList<>();
        estados.stream().
                forEach(estadoAbastecimiento -> {
                    var efecto = efectoRepository.findById(estadoAbastecimiento.getEfecto().getEfectoId());
                    var clase = claseRepository.findByClaseId(efecto.get().getClaseId());
                    estadosResponse.add(EstadoAbastecimientoResponse.builder()
                            .estadoId(estadoAbastecimiento.getEstadoId())
                            .efecto(this.getBuildEfectoResponse(efecto, Optional.of(clase)))
                            .estadoId(estadoAbastecimiento.getEstadoId())
                            .build());
                });

        return  OrganizacionResponse.builder()
                .nombre(org.getNombre())
                .organizacionId(org.getOrganizacionId())
                .posicion(org.getPosicion())
                .estados(estadosResponse)
                .build();

    }

    private OrganizacionResponse getBuildOrganizacionresponse(Optional<Organizacion> org) {
        return OrganizacionResponse.builder()
                .organizacionId(org.get().getOrganizacionId())
                .nombre(org.get().getNombre())
                .estados(buildEstadoDtoList(darEfectosDeAlta(org.get())))
                .posicion(org.get().getPosicion())
                .build();
    }

    private Optional<Organizacion> saveOrganizacion(OrganizacionRequest request) {
        return  Optional.of(organizacionRepository.
                save(this.buildOrganizacionFromRequest(request)));

    }

    private Ubicacion buildUbicacionToSave(OrganizacionRequest request) {
        return Ubicacion.builder()
                .latitud(request.getPocision().getLatitud())
                .longitud(request.getPocision().getLongitud())
                .build();
    }

    private Optional<Ubicacion> saveUbicacion(OrganizacionRequest request) {
        return Optional.of(ubicacionRepository.
                save(buildUbicacionToSave(request)));
    }

    private Organizacion buildOrganizacionFromRequest(OrganizacionRequest request) {
        Optional<Ubicacion> ubi =  this.saveUbicacion(request);
        return Organizacion.builder()
                .nombre(request.getNombre())
                .posicion(ubi.get())
                .build();
    }

    private List<EstadoAbastecimientoResponse> buildEstadoDtoList(List<EstadoAbastecimiento> estados) {
        var responses = this.getEstadoAbastecimientoArrayList();
        estados.stream()
                .forEach(estadoAbastecimiento ->  {
                    var efectoOp = Optional.of(estadoAbastecimiento.getEfecto());
                    var  claseOp =  claseRepository.findById(efectoOp.get().getClaseId());
                    this.addEstadoAbastecimientoToResponseList(responses, estadoAbastecimiento, efectoOp, claseOp);
                });
       return responses;
    }

    private void addEstadoAbastecimientoToResponseList(List<EstadoAbastecimientoResponse> responses, EstadoAbastecimiento estadoAbastecimiento, Optional<Efecto> efectoOp, Optional<Clase> claseOp) {
        responses.add( EstadoAbastecimientoResponse.builder()
                .estadoId(estadoAbastecimiento.getEstadoId())
                .efecto(this.getBuildEfectoResponse(efectoOp, claseOp))
                .cantidadNecesaria(estadoAbastecimiento.getCantidadNecesaria())
                .build());
    }

    private EfectoResponse getBuildEfectoResponse(Optional<Efecto> efectoOp, Optional<Clase> claseOp) {
        return EfectoResponse.builder()
                .clase(claseOp.get()).tipo(efectoOp.get().getTipo())
                .efectoId(efectoOp.get().getEfectoId())
                .unidadMedida(efectoOp.get().getUnidadMedida())
                .cantidad(efectoOp.get().getCantidad())
                .descripcionTipo(efectoOp.get().getDescripcionTipo()).build();
    }

    private List<EstadoAbastecimientoResponse> getEstadoAbastecimientoArrayList() {
        List<EstadoAbastecimientoResponse> responses = new ArrayList<>();
        return responses;
    }

    public  List<EstadoAbastecimiento> darEfectosDeAlta(Organizacion request) {
        Efecto municion = efectoRepository.save(Efecto.builder()
                .claseId(1L)
                .cantidad(150L)
                .unidadMedida("kg")
                .tipo(1)
                .descripcionTipo("Municion")
                .organizacionId(request.getOrganizacionId())
                .build());

        Efecto racionamiento = efectoRepository.save(Efecto.builder()
                .claseId(2L)
                .cantidad(150L)
                .unidadMedida("kg")
                .tipo(2)
                .descripcionTipo("Racionamiento")
                .organizacionId(request.getOrganizacionId())
                .build());

        Efecto vestuario = efectoRepository.save(Efecto.builder()
                .claseId(3L)
                .cantidad(150L)
                .unidadMedida("kg")
                .tipo(3)
                .descripcionTipo("Vestuario")
                .organizacionId(request.getOrganizacionId())
                .build());

        Efecto combustible = efectoRepository.save(Efecto.builder()
                .claseId(4L)
                .cantidad(150L)
                .unidadMedida("lt")
                .tipo(4)
                .descripcionTipo("Combustible")
                .organizacionId(request.getOrganizacionId())
                .build());

        var  lista = this.getEstadoAbastecimientos();
        this.addEstadoAbastecimientosToList(request, municion, racionamiento, vestuario, combustible, lista);
        return lista;
    }

    private List<EstadoAbastecimiento> getEstadoAbastecimientos() {
        List<EstadoAbastecimiento> lista = new ArrayList<>();
        return lista;
    }

    private void addEstadoAbastecimientosToList(Organizacion request, Efecto municion, Efecto racionamiento, Efecto vestuario, Efecto combustible, List<EstadoAbastecimiento> lista) {
        lista.add(estadoAbastecimientoRepository.save(EstadoAbastecimiento.builder()
                .cantidadNecesaria(50)
                .efecto(municion)
                .estadoId(1L)
                .organizacionId(request.getOrganizacionId()).build()));

        lista.add(estadoAbastecimientoRepository.save(EstadoAbastecimiento.builder()
                .cantidadNecesaria(50)
                .efecto(racionamiento)
                .estadoId(1L)
                .organizacionId(request.getOrganizacionId()).build()));

        lista.add(estadoAbastecimientoRepository.save(EstadoAbastecimiento.builder()
                .cantidadNecesaria(50)
                .efecto(vestuario)
                .estadoId(1L)
                .organizacionId(request.getOrganizacionId()).build()));

        lista.add(estadoAbastecimientoRepository.save(EstadoAbastecimiento.builder()
                .cantidadNecesaria(50)
                .efecto(combustible)
                .estadoId(1L)
                .organizacionId(request.getOrganizacionId()).build()));
    }

}
