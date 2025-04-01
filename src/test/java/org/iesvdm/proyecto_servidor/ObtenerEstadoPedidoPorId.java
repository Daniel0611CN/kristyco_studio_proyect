package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObtenerEstadoPedidoPorId {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void obtenerEstado() {
        System.out.println("Estado del Pedido 3: " + pedidoRepository.getAllById(3L).getEstado().name());
    }

}
