package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class DTORegister {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
