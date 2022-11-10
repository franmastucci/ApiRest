package com.undef.api.Relog.controller;

import com.undef.api.Relog.model.request.EfectoRequest;
import com.undef.api.Relog.model.response.EfectoResponse;
import com.undef.api.Relog.service.EfectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/efecto")
public class EfectoController {

    @Autowired
    private EfectoService efectoService;

    @PostMapping
    public ResponseEntity<EfectoResponse> create(@Valid @RequestBody EfectoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(efectoService.create(request));
    }

}
