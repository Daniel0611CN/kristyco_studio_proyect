package org.iesvdm.proyecto_servidor.domain;

import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.proyecto_servidor.enums.TipoRol;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rol {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoRol rol;

}
