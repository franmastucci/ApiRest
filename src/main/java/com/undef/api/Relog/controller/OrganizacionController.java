package com.undef.api.Relog.controller;

import com.undef.api.Relog.model.request.OrganizacionRequest;
import com.undef.api.Relog.model.response.EfectoResponse;
import com.undef.api.Relog.model.response.MovimientoResponse;
import com.undef.api.Relog.model.response.MovimientoSimpleResponse;
import com.undef.api.Relog.model.response.OrganizacionResponse;
import com.undef.api.Relog.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizacion")
public class OrganizacionController {

    @Autowired
    OrganizacionService organizacionService;

    @PostMapping()
    public ResponseEntity<OrganizacionResponse> create(@RequestBody OrganizacionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacionService.create(request));
    }

    @GetMapping("/{organizacion_id}/movimientos")
    public ResponseEntity<List<MovimientoSimpleResponse>> getMovimientosByOrganizacionid(@PathVariable Long organizacion_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacionService.getMovimientos(organizacion_id));
    }

    @GetMapping("/{organizacion_id}")
    public ResponseEntity<OrganizacionResponse> getOrganizacionById(@PathVariable Long organizacion_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacionService.getOrganizacionById(organizacion_id));
    }

    @GetMapping("/{organizacion_id}/efectos")
    public ResponseEntity<List<EfectoResponse>> getEfectosByOrganizacionid(@PathVariable Long organizacion_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacionService.getEfectos(organizacion_id));
    }



}
