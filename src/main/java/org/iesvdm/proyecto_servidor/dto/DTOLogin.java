package org.iesvdm.proyecto_servidor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOLogin {

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String password;

}
