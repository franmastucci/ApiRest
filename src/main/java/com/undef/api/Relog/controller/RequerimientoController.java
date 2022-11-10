package com.undef.api.Relog.controller;

import com.undef.api.Relog.model.request.RequerimientoRequest;
import com.undef.api.Relog.model.response.*;
import com.undef.api.Relog.service.RequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/requerimiento")
public class RequerimientoController {

    @Autowired
    RequerimientoService requerimientoService;

    @PostMapping()
    public ResponseEntity<RequerimientoResponse> create(@Valid @RequestBody RequerimientoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requerimientoService.create(request));
    }

    @GetMapping("/solicitudes/{requerimiento_id}")
    public ResponseEntity<List<SolicitudDeRequerimientoResponse>> getSolicitudes(@PathVariable Long requerimiento_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requerimientoService.getSolicitudes(requerimiento_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequerimientoResponse> getOrganizacionById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requerimientoService.getRequerimientoById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> confirm(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requerimientoService.confirm(id));
    }

    @GetMapping("/{organizacion_id}/{requerimiento_id}")
    public ResponseEntity<String> agregarRequerimiento(@PathVariable Long requerimiento_id, @PathVariable Long organizacion_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requerimientoService.agregarRequerimiento(requerimiento_id, organizacion_id));
    }

}
