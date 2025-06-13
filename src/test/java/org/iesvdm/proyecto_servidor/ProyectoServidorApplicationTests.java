package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.model.domain.*;
import org.iesvdm.proyecto_servidor.model.enums.EstadoPago;
import org.iesvdm.proyecto_servidor.model.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.model.enums.MetodoPago;
import org.iesvdm.proyecto_servidor.service.PedidoService;
import org.iesvdm.proyecto_servidor.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class ProyectoServidorApplicationTests {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Test
    void contextLoads() {
        Categoria c1 = Categoria.builder()
                .nombre("Colección Aire")
                .descripcion("Colección de invitaciones relacionadas con elementos como el viento y el aire.")
                .build();

        Categoria c2 = Categoria.builder()
                .nombre("Colección Aqua")
                .descripcion("Colección de invitaciones relacionadas con elementos del mar.")
                .build();

        Categoria c3 = Categoria.builder()
                .nombre("Colección Nature")
                .descripcion("Colección de invitaciones relacionadas con elementos appassionati de la tierra.")
                .build();

        Proveedor pv1 = Proveedor.builder()
                .direccion("Calle Alcorcón, 15")
                .email("provMadrid@gmail.com")
                .telefono(633627751L)
                .build();

        Proveedor pv2 = Proveedor.builder()
                .direccion("Calle Andrés, 2")
                .email("provAnd@gmail.com")
                .telefono(614561791L)
                .build();

        Proveedor pv3 = Proveedor.builder()
                .direccion("Calle San Antonio, 5")
                .email("juan21@gmail.com")
                .telefono(614216789L)
                .build();

        Proveedor pv4 = Proveedor.builder()
                .direccion("Calle Mar Mediterráneo, 10")
                .email("mariaGema@gmail.com")
                .telefono(614564591L)
                .build();

        Producto pt1 = Producto.builder()
                .nombre("Alas de Cristal - 1")
                .descripcion("Invitación Alas de Cristal - Colección Aire")
                .precio(BigDecimal.valueOf(5))
                .stock(20L)
                .imagen("img/coleccion-aire/alas-de-cristal/1.webp")
                .categoria(c1)
                .proveedor(pv1)
                .build();

        productoService.saveOrGetIfExists(pt1);

        Producto pt2 = Producto.builder()
                .nombre("Alas de Cristal - 2")
                .descripcion("Invitación Alas de Cristal - Colección Aire")
                .precio(BigDecimal.valueOf(3.5))
                .stock(10L)
                .imagen("img/coleccion-aire/alas-de-cristal/2.webp")
                .categoria(c1)
                .proveedor(pv2)
                .build();

        productoService.saveOrGetIfExists(pt2);

        Producto pt3 = Producto.builder()
                .nombre("Brisa de Fiesta - 1")
                .descripcion("Invitación Brisa de Fiesta - Colección Aire")
                .precio(BigDecimal.valueOf(4))
                .stock(15L)
                .imagen("img/coleccion-aire/brisa-de-fiesta/1.webp")
                .categoria(c1)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt3);

        Producto pt4 = Producto.builder()
                .nombre("Brisa de Fiesta - 2")
                .descripcion("Invitación Brisa de Fiesta - Colección Aire")
                .precio(BigDecimal.valueOf(6))
                .stock(8L)
                .imagen("img/coleccion-aire/brisa-de-fiesta/2.webp")
                .categoria(c1)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt4);

        Producto pt5 = Producto.builder()
                .nombre("Caricia de Pluma - 1")
                .descripcion("Invitación Caricia de Pluma - Colección Aire")
                .precio(BigDecimal.valueOf(7))
                .stock(12L)
                .imagen("img/coleccion-aire/caricia-de-pluma/1.webp")
                .categoria(c1)
                .proveedor(pv2)
                .build();

        productoService.saveOrGetIfExists(pt5);

        Producto pt6 = Producto.builder()
                .nombre("Caricia de Pluma - 2")
                .descripcion("Invitación Caricia de Pluma - Colección Aire")
                .precio(BigDecimal.valueOf(5.5))
                .stock(14L)
                .imagen("img/coleccion-aire/caricia-de-pluma/2.webp")
                .categoria(c1)
                .proveedor(pv4)
                .build();

        productoService.saveOrGetIfExists(pt6);

        Producto pt7 = Producto.builder()
                .nombre("Sueños al Viento - 1")
                .descripcion("Invitación Sueños al Viento - Colección Aire")
                .precio(BigDecimal.valueOf(4.5))
                .stock(9L)
                .imagen("img/coleccion-aire/sueños-al-viento/1.webp")
                .categoria(c1)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt7);

        Producto pt8 = Producto.builder()
                .nombre("Sueños al Viento - 2")
                .descripcion("Invitación Sueños al Viento - Colección Aire")
                .precio(BigDecimal.valueOf(6.5))
                .stock(11L)
                .imagen("img/coleccion-aire/sueños-al-viento/2.webp")
                .categoria(c1)
                .proveedor(pv2)
                .build();

        productoService.saveOrGetIfExists(pt8);

        Producto pt9 = Producto.builder()
                .nombre("Sueños al Viento - 3")
                .descripcion("Invitación Sueños al Viento - Colección Aire")
                .precio(BigDecimal.valueOf(3.8))
                .stock(13L)
                .imagen("img/coleccion-aire/sueños-al-viento/3.webp")
                .categoria(c1)
                .proveedor(pv1)
                .build();

        productoService.saveOrGetIfExists(pt9);

        Producto pt10 = Producto.builder()
                .nombre("Susurro de Mariposa - 1")
                .descripcion("Invitación Susurro de Mariposa - Colección Aire")
                .precio(BigDecimal.valueOf(5.2))
                .stock(7L)
                .imagen("img/coleccion-aire/susurro-de-mariposa/1.webp")
                .categoria(c1)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt10);

        Producto pt11 = Producto.builder()
                .nombre("Susurro de Mariposa - 2")
                .descripcion("Invitación Susurro de Mariposa - Colección Aire")
                .precio(BigDecimal.valueOf(8))
                .stock(6L)
                .imagen("img/coleccion-aire/susurro-de-mariposa/2.webp")
                .categoria(c1)
                .proveedor(pv2)
                .build();

        productoService.saveOrGetIfExists(pt11);

        Producto pt12 = Producto.builder()
                .nombre("Vuelo de Amor - 1")
                .descripcion("Invitación Vuelo de Amor - Colección Aire")
                .precio(BigDecimal.valueOf(4.2))
                .stock(10L)
                .imagen("img/coleccion-aire/vuelo-de-amor/1.webp")
                .categoria(c1)
                .proveedor(pv4)
                .build();

        productoService.saveOrGetIfExists(pt12);

        Producto pt13 = Producto.builder()
                .nombre("Vuelo de Amor - 2")
                .descripcion("Invitación Vuelo de Amor - Colección Aire")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aire/vuelo-de-amor/2.webp")
                .categoria(c1)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt13);

        Producto pt14 = Producto.builder()
                .nombre("Caracola")
                .descripcion("Invitación Caracola - Colección Aqua")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aqua/caracola/1.webp")
                .categoria(c2)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt14);

        Producto pt15 = Producto.builder()
                .nombre("Coral")
                .descripcion("Invitación Coral - Colección Aqua")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aqua/coral/1.webp")
                .categoria(c2)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt15);

        Producto pt16 = Producto.builder()
                .nombre("Espuma de Mar")
                .descripcion("Invitación Espuma de Mar - Colección Aqua")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aqua/espuma-de-mar/1.webp")
                .categoria(c2)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt16);

        Producto pt17 = Producto.builder()
                .nombre("Estrella de Mar")
                .descripcion("Invitación Estrella de Mar - Colección Aqua")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aqua/estrella-de-mar/1.webp")
                .categoria(c2)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt17);

        Producto pt18 = Producto.builder()
                .nombre("Océano")
                .descripcion("Invitación Océano - Colección Aqua")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-aqua/oceano/1.webp")
                .categoria(c2)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt18);

        Producto pt19 = Producto.builder()
                .nombre("Aria")
                .descripcion("Invitación Aria - Colección Nature")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-nature/aria/1.webp")
                .categoria(c3)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt19);

        Producto pt20 = Producto.builder()
                .nombre("Indie")
                .descripcion("Invitación Indie - Colección Nature")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-nature/indie/1.webp")
                .categoria(c3)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt20);

        Producto pt21 = Producto.builder()
                .nombre("Kaia")
                .descripcion("Invitación Kaia - Colección Nature")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-nature/kaia/1.webp")
                .categoria(c3)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt21);

        Producto pt22 = Producto.builder()
                .nombre("Zoe")
                .descripcion("Invitación Zoe - Colección Nature")
                .precio(BigDecimal.valueOf(6.8))
                .stock(12L)
                .imagen("img/coleccion-nature/zoe/1.webp")
                .categoria(c3)
                .proveedor(pv3)
                .build();

        productoService.saveOrGetIfExists(pt22);

        Usuario u1 = Usuario.builder()
                .nombre("María")
                .apellido1("Ruiz")
                .apellido2("León")
                .email("maria22@gmail.com")
                .telefono("681754114")
                .direccion("Calle Salamanca, 14")
                .password("1a?24#9183aniap1")
                .build();

        Usuario u2 = Usuario.builder()
                .nombre("Andrés")
                .apellido1("Pérez")
                .apellido2("Villarubia")
                .email("andresperezv@outlook.es")
                .telefono("687514901")
                .direccion("Calle Rio Chillar, 10")
                .password("1a?9138ANsidnas")
                .build();

        Usuario u3 = Usuario.builder()
                .nombre("Jose Manuel")
                .apellido1("Cuevas")
                .apellido2("Sedeño")
                .email("jmcuesed19@gmail.com")
                .telefono("681917818")
                .direccion("Calle Baja, 1")
                .password("soyjosee33")
                .build();

        Usuario u4 = Usuario.builder()
                .nombre("Paula")
                .apellido1("Jiménez")
                .apellido2("Gutiérrez")
                .email("pljigu09@gmail.com")
                .telefono("686894374")
                .direccion("Calle San Andrés, 2")
                .password("22paulaJM")
                .build();

        Usuario u5 = Usuario.builder()
                .nombre("David")
                .apellido1("Tejón")
                .apellido2("Martínez")
                .email("dtm2@gmail.com")
                .telefono("681358236")
                .direccion("Av Clemente, 11")
                .password("dtM4?#futb0l")
                .build();

        Usuario u6 = Usuario.builder()
                .nombre("Andrea")
                .apellido1("Quero")
                .apellido2("Porras")
                .email("andreita10@gmail.com")
                .telefono("687715614")
                .direccion("Calle Libertad, 5")
                .password("991Aie#31")
                .build();

        Pedido p1 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Guadalquivir, 17")
                .total(BigDecimal.valueOf(47.85))
                .costeEnvio(BigDecimal.valueOf(35))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(20))
                        .metodo(MetodoPago.BIZUM)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u2)
                .productos(new HashSet<>(List.of(pt1, pt12, pt13)))
                .build();

        pedidoService.saveOrGetIfExists(p1);

        Pedido p2 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Guadiana, 2")
                .total(BigDecimal.valueOf(20.25))
                .costeEnvio(BigDecimal.valueOf(10))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(15))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u1)
                .productos(new HashSet<>(List.of(pt2, pt13)))
                .build();

        pedidoService.saveOrGetIfExists(p2);

        Pedido p3 = Pedido.builder()
                .estado(EstadoPedido.EN_CAMINO)
                .direccion("Avenida del Sol, 5")
                .total(BigDecimal.valueOf(35.5))
                .costeEnvio(BigDecimal.valueOf(15))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(10))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u4)
                .productos(new HashSet<>(List.of(pt3, pt11)))
                .build();

        pedidoService.saveOrGetIfExists(p3);

        Pedido p4 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Luna, 12")
                .total(BigDecimal.valueOf(28.75))
                .costeEnvio(BigDecimal.valueOf(12))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(10.5))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u2)
                .productos(new HashSet<>(List.of(pt6, pt10, pt12)))
                .build();

        pedidoService.saveOrGetIfExists(p4);

        Pedido p5 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Plaza Mayor, 3")
                .total(BigDecimal.valueOf(47.85))
                .costeEnvio(BigDecimal.valueOf(35))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(20))
                        .metodo(MetodoPago.BIZUM)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u2)
                .productos(new HashSet<>(List.of(pt1, pt7, pt13)))
                .build();

        pedidoService.saveOrGetIfExists(p5);

        Pedido p6 = Pedido.builder()
                .estado(EstadoPedido.EN_CAMINO)
                .direccion("Calle Estrella, 8")
                .total(BigDecimal.valueOf(15.9))
                .costeEnvio(BigDecimal.valueOf(8))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(13.5))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.CANCELADO)
                        .build())
                .usuario(u5)
                .productos(new HashSet<>(List.of(pt3, pt9)))
                .build();

        pedidoService.saveOrGetIfExists(p6);

        Pedido p7 = Pedido.builder()
                .estado(EstadoPedido.ENTREGADO)
                .direccion("Calle Olmo, 25")
                .total(BigDecimal.valueOf(42.3))
                .costeEnvio(BigDecimal.valueOf(18))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(27.55))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u6)
                .productos(new HashSet<>(List.of(pt2)))
                .build();

        pedidoService.saveOrGetIfExists(p7);

        Pedido p8 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Avenida Paz, 14")
                .total(BigDecimal.valueOf(25.6))
                .costeEnvio(BigDecimal.valueOf(10))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(32))
                        .metodo(MetodoPago.BIZUM)
                        .estado(EstadoPago.CANCELADO)
                        .build())
                .usuario(u1)
                .productos(new HashSet<>(List.of(pt5, pt11)))
                .build();

        pedidoService.saveOrGetIfExists(p8);

        Pedido p9 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Tajo, 7")
                .total(BigDecimal.valueOf(55.45))
                .costeEnvio(BigDecimal.valueOf(25))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(22.35))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u3)
                .productos(new HashSet<>(List.of(pt4, pt8)))
                .build();

        pedidoService.saveOrGetIfExists(p9);

        Pedido p10 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Ebro, 9")
                .total(BigDecimal.valueOf(33.15))
                .costeEnvio(BigDecimal.valueOf(15))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(14))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u4)
                .productos(new HashSet<>(List.of(pt11)))
                .build();

        pedidoService.saveOrGetIfExists(p10);

        Pedido p11 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Duero, 11")
                .total(BigDecimal.valueOf(19.8))
                .costeEnvio(BigDecimal.valueOf(9))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(5))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u5)
                .productos(new HashSet<>(List.of(pt9, pt12)))
                .build();

        pedidoService.saveOrGetIfExists(p11);

        Pedido p12 = Pedido.builder()
                .estado(EstadoPedido.EN_CAMINO)
                .direccion("Calle Río Miño, 4")
                .total(BigDecimal.valueOf(48.9))
                .costeEnvio(BigDecimal.valueOf(22))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(17.9))
                        .metodo(MetodoPago.BIZUM)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u2)
                .productos(new HashSet<>(List.of(pt6)))
                .build();

        pedidoService.saveOrGetIfExists(p12);

        Pedido p13 = Pedido.builder()
                .estado(EstadoPedido.ENTREGADO)
                .direccion("Avenida Libertad, 20")
                .total(BigDecimal.valueOf(37.25))
                .costeEnvio(BigDecimal.valueOf(17))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(10))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u1)
                .productos(new HashSet<>(List.of(pt11)))
                .build();

        pedidoService.saveOrGetIfExists(p13);

        Pedido p14 = Pedido.builder()
                .estado(EstadoPedido.ENTREGADO)
                .direccion("Calle Río Segura, 6")
                .total(BigDecimal.valueOf(29.4))
                .costeEnvio(BigDecimal.valueOf(13))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(11.5))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.CANCELADO)
                        .build())
                .usuario(u4)
                .productos(new HashSet<>(List.of(pt13)))
                .build();

        pedidoService.saveOrGetIfExists(p14);

        Pedido p15 = Pedido.builder()
                .estado(EstadoPedido.EN_CAMINO)
                .direccion("Calle Río Júcar, 15")
                .total(BigDecimal.valueOf(62.1))
                .costeEnvio(BigDecimal.valueOf(30))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(25))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u6)
                .productos(new HashSet<>(List.of(pt3)))
                .build();

        pedidoService.saveOrGetIfExists(p15);

        Pedido p16 = Pedido.builder()
                .estado(EstadoPedido.ENTREGADO)
                .direccion("Calle Río Turia, 18")
                .total(BigDecimal.valueOf(23.95))
                .costeEnvio(BigDecimal.valueOf(11))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(7))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.CANCELADO)
                        .build())
                .usuario(u5)
                .productos(new HashSet<>(List.of(pt4, pt7)))
                .build();

        pedidoService.saveOrGetIfExists(p16);

        Pedido p17 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Plaza España, 1")
                .total(BigDecimal.valueOf(50.7))
                .costeEnvio(BigDecimal.valueOf(23))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(19.95))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u6)
                .productos(new HashSet<>(List.of(pt3, pt10)))
                .build();

        pedidoService.saveOrGetIfExists(p17);

        Pedido p18 = Pedido.builder()
                .estado(EstadoPedido.EN_CAMINO)
                .direccion("Calle Río Pisuerga, 22")
                .total(BigDecimal.valueOf(31.85))
                .costeEnvio(BigDecimal.valueOf(14))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(12.35))
                        .metodo(MetodoPago.BIZUM)
                        .estado(EstadoPago.COMPLETADO)
                        .build())
                .usuario(u1)
                .productos(new HashSet<>(List.of(pt9)))
                .build();

        pedidoService.saveOrGetIfExists(p18);

        Pedido p19 = Pedido.builder()
                .estado(EstadoPedido.ENTREGADO)
                .direccion("Avenida Castilla, 30")
                .total(BigDecimal.valueOf(44.6))
                .costeEnvio(BigDecimal.valueOf(19))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(22))
                        .metodo(MetodoPago.TARJETA)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u3)
                .productos(new HashSet<>(List.of(pt11, pt12)))
                .build();

        pedidoService.saveOrGetIfExists(p19);

        Pedido p20 = Pedido.builder()
                .estado(EstadoPedido.PENDIENTE)
                .direccion("Calle Río Genil, 13")
                .total(BigDecimal.valueOf(27.35))
                .costeEnvio(BigDecimal.valueOf(12))
                .pago(Pago.builder()
                        .monto(BigDecimal.valueOf(19))
                        .metodo(MetodoPago.EFECTIVO)
                        .estado(EstadoPago.PENDIENTE)
                        .build())
                .usuario(u6)
                .productos(new HashSet<>(List.of(pt3)))
                .build();

        pedidoService.saveOrGetIfExists(p20);

    }

}
