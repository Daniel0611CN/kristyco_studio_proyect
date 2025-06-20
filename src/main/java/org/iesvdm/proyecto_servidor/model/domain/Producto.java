package org.iesvdm.proyecto_servidor.model.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @ToString.Exclude
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    @ToString.Exclude
    private Proveedor proveedor;

    @ManyToMany(mappedBy = "productos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Builder.Default
    @ToString.Exclude
    @JsonIgnore
    private Set<Pedido> pedidos = new HashSet<>();

}