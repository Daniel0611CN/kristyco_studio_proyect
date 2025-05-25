package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.model.domain.Rol;
import org.iesvdm.proyecto_servidor.model.enums.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> { Optional<Rol> findByRol(TipoRol rol); }
