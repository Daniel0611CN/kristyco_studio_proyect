package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class DTORegister {
    private String username;
    private String apellido1;
    private String apellido2;
    private String email;
    private Long telefono;
    private String direccion;
    private String password;
    private Set<String> roles;
}
