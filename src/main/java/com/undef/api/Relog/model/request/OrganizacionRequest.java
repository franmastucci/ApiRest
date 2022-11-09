package com.undef.api.Relog.model.request;

import com.undef.api.Relog.model.entity.Ubicacion;
import lombok.Data;

@Data
public class OrganizacionRequest {

    private String nombre;

    private Ubicacion pocision;

}
