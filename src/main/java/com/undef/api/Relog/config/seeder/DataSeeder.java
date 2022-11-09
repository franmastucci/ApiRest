package com.undef.api.Relog.config.seeder;

import com.undef.api.Relog.enums.ClaseEnum;
import com.undef.api.Relog.model.entity.clase.Clase;
import com.undef.api.Relog.model.entity.estado.EstadoPersistent;
import com.undef.api.Relog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    ClaseRepository claseRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("seeder...");
        this.validateClaseRepository();
        this.validateEstadoRepository();
    }

    private void validateEstadoRepository() {
        if(estadoRepository.findAll().isEmpty())
            this.createEstados();
    }

    private void validateClaseRepository() {
        if(claseRepository.findAll().isEmpty())
            this.createClases();
    }

    private void createEstados() {
        estadoRepository.save(EstadoPersistent.builder()
                .id(1L).descripcion("Con Stock").build());

        estadoRepository.save(EstadoPersistent.builder()
                .id(2L).descripcion("Con Faltantes").build());
    }

    private void createClases() {
        claseRepository.save(Clase.builder()
                .descripcion("Descripcion de la municion")
                .tipo(ClaseEnum.MUNICION.getName())
                .build());

        claseRepository.save(Clase.builder()
                .tipo(ClaseEnum.RACIONAMIENTO.getName())
                .descripcion("Descripcion del racionamiento")
                .build());

        claseRepository.save(Clase.builder()
                .descripcion("Descripcion del vestuario")
                .tipo(ClaseEnum.VESTUARIO.getName())
                .build());

        claseRepository.save(Clase.builder()
                .descripcion("Descripcion del combustible")
                .tipo(ClaseEnum.COMBUSTIBLE.getName())
                .build());
    }

}
