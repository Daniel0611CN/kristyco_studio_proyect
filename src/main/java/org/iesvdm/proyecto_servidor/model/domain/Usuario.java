package org.iesvdm.proyecto_servidor.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String apellido1;

    @Column(length = 20)
    private String apellido2;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 9, unique = true, nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    @JsonIgnore
    private Set<Pedido> pedidos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    @ToString.Exclude
    private Set<Rol> roles = new HashSet<>();

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean enabled = false;

    public Usuario(String username, String apellido1, String apellido2, String email, String telefono, String direccion, String password) {

        this.nombre = username;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.password = password;
        this.locked = false;
        this.enabled = false;

    }
}
