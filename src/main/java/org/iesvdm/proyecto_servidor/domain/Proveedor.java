package org.iesvdm.proyecto_servidor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    //@JsonIgnore
    private Set<Producto> productosProveedor = new HashSet<>();

}
