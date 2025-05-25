package org.iesvdm.proyecto_servidor.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(precision = 9, unique = true)
    private Long telefono;

    @Email(regexp = "^[a-zA-Z0-9]+@g.educaand.es$|^[a-zA-Z0-9-]+@gmail.com$",
            message = "El email debe ser de @g.educaand.es o @gmail.com")
    @Column(length = 45, unique = true)
    private String email;

    private String direccion;

    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private Set<Producto> productosProveedor = new HashSet<>();

}
