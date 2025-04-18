package org.iesvdm.proyecto_servidor;

import org.iesvdm.proyecto_servidor.model.domain.Rol;
import org.iesvdm.proyecto_servidor.model.enums.TipoRol;
import org.iesvdm.proyecto_servidor.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRoles {

    @Autowired
    private RolRepository rolRepository;

    @Test
    void createRol() {
        if (rolRepository.findByRol(TipoRol.ROL_ADMIN).isEmpty()) {
            rolRepository.save(new Rol(null, TipoRol.ROL_ADMIN));
        }
        if (rolRepository.findByRol(TipoRol.ROL_USER).isEmpty()) {
            rolRepository.save(new Rol(null, TipoRol.ROL_USER));
        }
    }

}
