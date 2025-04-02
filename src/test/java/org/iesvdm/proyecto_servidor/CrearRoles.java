package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.domain.Rol;
import org.iesvdm.proyecto_servidor.enums.TipoRol;
import org.iesvdm.proyecto_servidor.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrearRoles {

    @Autowired
    private RolRepository rolRepository;

    @Test
    void crearRoles() {
        if (rolRepository.findByRol(TipoRol.ROL_ADMIN).isEmpty()) {
            rolRepository.save(new Rol(null, TipoRol.ROL_ADMIN));
        }
        if (rolRepository.findByRol(TipoRol.ROL_USER).isEmpty()) {
            rolRepository.save(new Rol(null, TipoRol.ROL_USER));
        }
    }

}
