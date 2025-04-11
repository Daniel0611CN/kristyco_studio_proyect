package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.domain.Producto;
import org.iesvdm.proyecto_servidor.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestProveedores {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    void obtenerProductos() {
        List<Producto> listaProductos = proveedorRepository.getProductosByProveedor_Id(1L);
        listaProductos.forEach(System.out::println);
    }

}
