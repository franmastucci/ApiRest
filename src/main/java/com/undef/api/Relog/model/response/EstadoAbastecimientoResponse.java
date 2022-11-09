package com.undef.api.Relog.model.response;

import com.undef.api.Relog.model.entity.Efecto;
import com.undef.api.Relog.model.entity.movimiento.Movimiento;
import com.undef.api.Relog.model.entity.movimiento.MovimientoPersistent;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class EstadoAbastecimientoResponse {

    private Long estadoId;

    private Integer cantidadNecesaria;

    private EfectoResponse efecto;

}
