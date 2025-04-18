package org.iesvdm.proyecto_servidor.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class DTORegister {

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    @Size(min = 3, max = 15, message = "El nombre de usuario debe tener entre 3 y 15 caracteres.")
    private String username;

    @NotBlank(message = "El primer apellido no puede estar vacío.")
    @Size(max = 20, message = "El primer apellido no puede tener más de 20 caracteres.")
    private String apellido1;

    @Size(max = 20, message = "El segundo apellido no puede tener más de 20 caracteres.")
    private String apellido2;

    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El email debe tener un formato válido.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|outlook\\.es|g\\.educaand\\.es)$", message = "El email debe ser uno de los siguientes: @gmail.com, @outlook.es, @g.educaand.es.")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío.")
    @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener exactamente 9 dígitos.")
    private Long telefono;

    @NotBlank(message = "La dirección no puede estar vacía.")
    private String direccion;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres.")
    @Pattern(regexp = "^(?=(.*[a-z]){3})(?=(.*[A-Z]){2})(?=(.*\\d){1})(?=(.*[!@#$%^&*]){1}).{8,20}$",
            message = "La contraseña debe contener al menos 3 letras minúsculas, 2 mayúsculas, 1 número y 1 carácter especial.")
    private String password;

    private Set<String> roles;
}
