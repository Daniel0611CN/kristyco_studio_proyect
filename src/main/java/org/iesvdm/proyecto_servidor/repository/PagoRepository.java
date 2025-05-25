package org.iesvdm.proyecto_servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.domain.Pago;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> { List<Pago> findAllByPedido_UsuarioOrderById(Usuario pedidoUsuario); }
