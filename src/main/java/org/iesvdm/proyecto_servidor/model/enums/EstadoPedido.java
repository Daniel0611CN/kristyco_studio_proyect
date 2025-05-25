package org.iesvdm.proyecto_servidor.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPedido {
        PENDIENTE,
        EN_CAMINO,
        ENTREGADO,
        CANCELADO
}
