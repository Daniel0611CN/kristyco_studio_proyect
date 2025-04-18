package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.iesvdm.proyecto_servidor.model.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.domain.Pedido;
import org.iesvdm.proyecto_servidor.model.domain.Pago;
import org.iesvdm.proyecto_servidor.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoService
//        implements BasicServiceInterface<Pedido>
{

    private final PedidoRepository pedidoRepository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PagoService pagoService;
    private final CategoriaRepository categoriaRepository;

    public Page<Pedido> all(Pageable pageable) { return this.pedidoRepository.findAll(pageable); }

    @Transactional
    public Pedido save(Pedido pedido) {
        if (pedido == null) throw new IllegalArgumentException("El pedido no puede ser null");

        if (pedido.getId() == null) {
            Usuario usuario = pedido.getUsuario();
            usuarioService.saveOrGetIfExists(usuario);
            usuario.getPedidos().add(pedido);
            pedido.setUsuario(usuario);

            Pago pago = pedido.getPago();
            pagoService.saveOrGetIfExists(pago);
            pago.setPedido(pedido);
            pedido.setPago(pago);

            Set<Producto> productos = pedido.getProductos();
            Set<Producto> aux = new HashSet<>();

            if (productos == null) {
                throw new IllegalArgumentException("Los productos del pedido no pueden ser null");
            }

            for (Producto producto : productos) {
                productoService.saveOrGetIfExists(producto);
                Producto finalProducto = producto;
                producto = productoService.oneOptional(producto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(finalProducto.getId(), Producto.class));
                producto.getPedidos().add(pedido);
                aux.add(producto);
            }
            pedido.setProductos(aux);

            return pedidoRepository.save(pedido);
        } else {
            return pedidoRepository.findById(pedido.getId())
                    .orElseThrow(() -> new EntityNotFoundException(pedido.getId(), Pedido.class));
        }
    }

    public Pedido one(Long id) {
        return this.pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Pedido.class));
    }

    public Pedido replace(Long id, Pedido pedido) {
        return this.pedidoRepository.findById(id).map( p -> {
                    if (id.equals(pedido.getId())) return this.pedidoRepository.save(pedido);
                    else throw new NotCouplingIdException(id, pedido.getId(), Pedido.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Pedido.class));
    }

    public void delete(Long id) {
        this.pedidoRepository.findById(id).map(p -> {
                    this.pedidoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Pedido.class));
    }

    public Set<Pedido> sortListPedidos(Usuario usuario) {
//        return this.pedidoRepository.getAllByUsuarioOrderById(usuario);
        return null;
    }

    public Set<Pedido> getPedidosByCategoriaId(Long id) {
        return categoriaRepository.getPedidosHasProductoByCategoriaId(id);
    }

    public List<Pedido> getPedidosAfterDate(LocalDateTime date) {
        return pedidoRepository.findByFechaAfter(date);
    }

    public Set<Pedido> getAllPedidoByCosteEnvioHigherThan(BigDecimal value) {
        return pedidoRepository.getDistinctByCosteEnvioGreaterThanOrderByCosteEnvioAsc(value);
    }

    public Set<Pedido> getAllByEstado(EstadoPedido estadoPedido) {
        return pedidoRepository.getAllByEstado(estadoPedido);
    }

    public List<Pedido> filter(Long id, String direccion, EstadoPedido estado, LocalDateTime fecha, BigDecimal costeEnvio) {
        if (id != null) {
            return pedidoRepository.findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(id, null, null, null, null);
        } else if (direccion != null) {
            return pedidoRepository.findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(null, direccion, null, null, null);
        } else if (estado != null) {
            return pedidoRepository.findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(null, null, estado, null, null);
        } else if (fecha != null) {
            return pedidoRepository.findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(null, null, null, fecha, null);
        } else {
            return pedidoRepository.findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(null, null, null, null, costeEnvio);
        }
    }

}
