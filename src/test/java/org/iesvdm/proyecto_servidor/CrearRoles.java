package org.iesvdm.miproyecto;

import org.iesvdm.miproyecto.domain.Rol;
import org.iesvdm.miproyecto.repository.RolRepository;
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
            rolRepository.save(new Rol(null, TipoRol.ROL_ADMIN, null));
        }
        if (rolRepository.findByRol(TipoRol.ROL_USER).isEmpty()) {
            rolRepository.save(new Rol(null, TipoRol.ROL_USER, null));
        }
    }

}
