package com.undef.api.Relog.controller;

import com.undef.api.Relog.model.request.MovimientoRequest;
import com.undef.api.Relog.model.response.MovimientoResponse;
import com.undef.api.Relog.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @PostMapping("/{tipo}")
    public ResponseEntity<MovimientoResponse> crearMovoimiento(@Valid  @RequestBody MovimientoRequest request, @PathVariable("tipo") Long tipo){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.generarMovimiento(request,tipo));
    }

}
