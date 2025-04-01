package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.domain.Rol;
import org.iesvdm.proyecto_servidor.enums.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRol(TipoRol rol);
}
