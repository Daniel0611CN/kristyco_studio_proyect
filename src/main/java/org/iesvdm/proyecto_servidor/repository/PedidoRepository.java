package org.iesvdm.proyecto_servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.iesvdm.proyecto_servidor.model.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.model.domain.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findAll(Pageable pageable);
    List<Pedido> findByFechaAfter(LocalDateTime fecha);
    @Query("SELECT p.usuario.id, COUNT(p) FROM Pedido p GROUP BY p.usuario.id")
    List<Object[]> findAllGroupByUsuario();
    Set<Pedido> getDistinctByCosteEnvioGreaterThanOrderByCosteEnvioAsc(BigDecimal costeEnvioIsGreaterThan);
    Set<Pedido> getAllByEstado(EstadoPedido estado);
    List<Pedido> findByIdOrDireccionOrEstadoOrFechaOrCosteEnvioOrderById(Long id, String direccion, EstadoPedido estado, LocalDateTime fecha, BigDecimal costeEnvio);
    Page<Pedido> findByDireccionContaining(String direccion, Pageable pageable);
    Page<Pedido> findByEstado(EstadoPedido estado, Pageable pageable);
}
