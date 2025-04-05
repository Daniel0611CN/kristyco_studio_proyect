package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.domain.Pedido;
import org.iesvdm.proyecto_servidor.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findAll(Pageable pageable);

    List<Pedido> findByFechaAfter(LocalDateTime fecha);

    @Query("SELECT p.usuario.id, COUNT(p) FROM Pedido p GROUP BY p.usuario.id")
    List<Object[]> findAllGroupByUsuario();

    Pedido getAllById(Long id);

    Set<Pedido> getDistinctByCosteEnvioGreaterThanOrderByCosteEnvioAsc(BigDecimal costeEnvioIsGreaterThan);

    Set<Pedido> getAllByEstado(EstadoPedido estado);

    List<Pedido> findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(Long id, String direccion, EstadoPedido estado, LocalDateTime fecha, BigDecimal costeEnvio);
    
}
