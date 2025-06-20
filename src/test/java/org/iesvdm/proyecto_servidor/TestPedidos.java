package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.model.domain.Pedido;
import org.iesvdm.proyecto_servidor.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TestPedidos {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void agruparPorUsuarios() {
        List<Object[]> resultados = pedidoRepository.findAllGroupByUsuario();
        for (Object[] resultado : resultados) {
            Long usuarioId = (Long) resultado[0];
            Long conteo = (Long) resultado[1];
            System.out.println("Usuario ID: " + usuarioId + ", Pedidos: " + conteo);
        }
    }

    @Test
    void filtrarPorFechaPosterior() {
        LocalDateTime fecha = LocalDateTime.of(2025, 3, 5, 13, 29, 31);
        List<Pedido> listadoPedidos = pedidoRepository.findByFechaAfter(fecha);
        System.out.println(listadoPedidos);
    }

}
