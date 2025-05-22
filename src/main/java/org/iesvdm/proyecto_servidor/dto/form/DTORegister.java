package org.iesvdm.proyecto_servidor.dto.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class DTORegister {

    @NotBlank(message = "{nombre.required}")
    @Size(min = 3, message = "{nombre.minlength}")
    @Size(max = 20, message = "{nombre.maxlength}")
    private String username;

    @NotBlank(message = "{apellido1.required}")
    @Size(max = 20, message = "{apellido1.maxlength}")
    private String apellido1;

    @Size(max = 20, message = "{apellido2.maxlength}")
    private String apellido2;

    @NotBlank(message = "{email.required}")
    @Email(regexp = "^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "{email.pattern}")
    private String email;

    @NotBlank(message = "{telefono.required}")
    @Pattern(regexp = "^[0-9]{9}$", message = "{telefono.pattern}")
    private String telefono;

    @NotBlank(message = "{direccion.required}")
    private String direccion;

    @NotBlank(message = "{password.required}")
    @Size(max = 24, message = "{password.maxlength}")
    @Pattern(regexp = "^(?=(.*[a-z]){3})(?=(.*[A-Z]){2})(?=(.*\\d){1})(?=(.*[!@#$%^&*]){1}).{8,24}$", message = "{password.pattern}")
    private String password;

    private Set<String> roles;
}
