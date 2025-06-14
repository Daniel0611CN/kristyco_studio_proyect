package org.iesvdm.proyecto_servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOPasswordValidation {
    private String token;
    private String oldPassword;
}
