package org.iesvdm.proyecto_servidor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
//@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 15, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String apellido1;

    @Column(length = 20)
    private String apellido2;

    @Column(length = 45, unique = true, nullable = false)
    private String email;

    @Column(precision = 9, unique = true)
    private Long telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(length = 32, nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    //@JsonIgnore
    private Set<Pedido> pedidos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String nombre, String email, String password) {

        this.nombre = nombre;
        this.email = email;
        this.password = password;

    }

}
