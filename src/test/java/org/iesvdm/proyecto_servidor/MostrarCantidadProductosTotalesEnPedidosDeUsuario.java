package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.domain.Producto;
import org.iesvdm.proyecto_servidor.repository.ProductoRepository;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.*;

@SpringBootTest
public class MostrarCantidadProductosTotalesEnPedidosDeUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void mostrarProductos() {
        List<Producto> listadoProductosTodosPedidosRealizadosPorUsuarioId = usuarioRepository.findByPedidos_Productos(1L).stream().sorted(Comparator.comparingInt(value -> Math.toIntExact(value.getId()))).toList();
        listadoProductosTodosPedidosRealizadosPorUsuarioId.forEach(System.out::println);
        System.out.println("El total de productos pedidos por el usuario 1 es " + listadoProductosTodosPedidosRealizadosPorUsuarioId.size());
        Map<String, Integer> cantidadesProductosEnId = new LinkedHashMap<>();
        for (int i = 1; i <= productoRepository.count(); i++) {
            int count = 0;
            for (Producto producto : listadoProductosTodosPedidosRealizadosPorUsuarioId) {
                if (i == producto.getId()) {
                    count++;
                }
                cantidadesProductosEnId.put("Producto " + i, count);
            }
        }
        cantidadesProductosEnId.entrySet().forEach(System.out::println);
    }

}
