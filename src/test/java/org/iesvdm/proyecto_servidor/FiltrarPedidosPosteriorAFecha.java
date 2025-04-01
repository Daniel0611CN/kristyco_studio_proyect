package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.domain.Pedido;
import org.iesvdm.proyecto_servidor.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class FiltrarPedidosPosteriorAFecha {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void filtrarPorFechaPosterior() {
        LocalDateTime fecha = LocalDateTime.of(2025, 3, 5, 13, 29, 31);
        List<Pedido> listadoPedidos = pedidoRepository.findByFechaAfter(fecha);
        System.out.println(listadoPedidos);
    }

}
