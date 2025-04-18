package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.domain.Pago;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@SpringBootTest
public class TotalMontoPorUsuario {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void montoTotal() {
        List<Pago> listadoPagosUsuario1 = new ArrayList<>();
        double sumaMontoPedidosUsuario1 = pagoRepository.findAllByPedido_UsuarioOrderById(usuarioRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException(
                        Objects.requireNonNull(usuarioRepository.findById(1L).orElse(null)).getId(), Usuario.class))
                )
                .stream().mapToDouble(p -> { listadoPagosUsuario1.add(p); return p.getMonto().doubleValue(); }).sum();
        System.out.println("Monto Total (Usuario 1): " + sumaMontoPedidosUsuario1 + "€");
        listadoPagosUsuario1.forEach(System.out::println);
    }

}
