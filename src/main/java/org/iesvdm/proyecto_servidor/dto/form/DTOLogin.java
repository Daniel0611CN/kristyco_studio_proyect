package org.iesvdm.proyecto_servidor.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOLogin {

    @NotBlank(message = "El nombre es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
