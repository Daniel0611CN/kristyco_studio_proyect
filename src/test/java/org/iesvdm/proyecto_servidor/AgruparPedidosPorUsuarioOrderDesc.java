package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.List;

@SpringBootTest
public class AgruparPedidosPorUsuarioOrderDesc {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void filtrarPorFechaPosterior() {
        List<Object[]> resultados = pedidoRepository.findAllGroupByUsuario();
        for (Object[] resultado : resultados) {
            Long usuarioId = (Long) resultado[0];
            Long conteo = (Long) resultado[1];
            System.out.println("Usuario ID: " + usuarioId + ", Pedidos: " + conteo);
        }
    }

}
