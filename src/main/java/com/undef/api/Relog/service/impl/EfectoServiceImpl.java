package com.undef.api.Relog.service.impl;

import com.undef.api.Relog.enums.ClaseEnum;
import com.undef.api.Relog.model.entity.*;
import com.undef.api.Relog.model.entity.clase.*;
import com.undef.api.Relog.model.request.EfectoRequest;
import com.undef.api.Relog.model.response.EfectoResponse;
import com.undef.api.Relog.repository.ClaseRepository;
import com.undef.api.Relog.repository.EfectoRepository;
import com.undef.api.Relog.service.EfectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EfectoServiceImpl  implements EfectoService {

    @Autowired
    private EfectoRepository efectoRepository;
    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public EfectoResponse create(EfectoRequest request) {
        return buildEfectoResponse(efectoRepository.save(buildEfecto(request)));
    }


    public Efecto buildEfecto(EfectoRequest request) {
        return Efecto.builder()
                .claseId(request.getClaseId())
                .tipo(request.getTipo())
                .cantidad(request.getCantidad())
                .unidadMedida(request.getUnidadMedida())
                .descripcionTipo(request.getDescripcionTipo())
                .build();
    }

     public EfectoResponse buildEfectoResponse(Efecto efecto) {
         var claseFromRepo = this.getClaseById(efecto);
         var clase = this.seleccionarClase(claseFromRepo);

         return EfectoResponse.builder()
                .efectoId(efecto.getEfectoId())
                .cantidad(efecto.getCantidad())
                .tipo(efecto.getTipo())
                .unidadMedida(efecto.getUnidadMedida())
                .descripcionTipo(efecto.getDescripcionTipo())
                .clase(clase).build();
     }

    private Optional<Clase> getClaseById(Efecto efecto) {
        return claseRepository.findById(efecto.getClaseId());
    }

    @Override
     public Clase seleccionarClase(Optional<Clase> claseFromRepo) {
        Clase clase = null;

        if (claseFromRepo.get().getTipo().equalsIgnoreCase(ClaseEnum.RACIONAMIENTO.getName()))
            clase= new Racionamiento();
        if (claseFromRepo.get().getTipo().equalsIgnoreCase(ClaseEnum.MUNICION.getName()))
            clase= new Municion();
        if (claseFromRepo.get().getTipo().equalsIgnoreCase(ClaseEnum.COMBUSTIBLE.getName()))
            clase= new Combustible();
        if (claseFromRepo.get().getTipo().equalsIgnoreCase(ClaseEnum.VESTUARIO.getName()))
            clase= new Vestuario();

        this.setClase(claseFromRepo, clase);
        return clase;
    }

    private void setClase(Optional<Clase> claseFromRepo, Clase clase) {
        clase.setClaseId(claseFromRepo.get().getClaseId());
        clase.setDescripcion(claseFromRepo.get().getDescripcion());
        clase.setTipo(claseFromRepo.get().getTipo());
    }

}
