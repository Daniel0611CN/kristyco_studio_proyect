package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT p.productos FROM Pedido p WHERE p.usuario.id = :usuarioId")
    List<Producto> findByPedidos_Productos(@Param("usuarioId") Long usuarioId);

    Optional<Usuario> findByNombre(String username);

    Boolean existsByNombre(String username);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableUsuario(String email);

}
