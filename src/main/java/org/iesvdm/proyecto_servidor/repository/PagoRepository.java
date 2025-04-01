package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.domain.Pago;
import org.iesvdm.proyecto_servidor.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findAllByPedido_UsuarioOrderById(Usuario pedidoUsuario);

}
