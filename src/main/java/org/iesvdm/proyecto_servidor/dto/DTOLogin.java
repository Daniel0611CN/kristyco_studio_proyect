package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOLogin {
    private String username;
    private String password;
}
