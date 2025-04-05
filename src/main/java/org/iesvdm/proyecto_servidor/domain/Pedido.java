package org.iesvdm.proyecto_servidor.domain;

import com.fasterxml.jackson.annotation.*;
import org.iesvdm.proyecto_servidor.enums.EstadoPedido;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
//@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @Column(length = 80)
    private String direccion;

    @Column(scale = 4)
    private BigDecimal total;

    @Column(scale = 4)
    private BigDecimal costeEnvio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Usuario usuario;

    @ManyToMany
    @Builder.Default
    @ToString.Exclude
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

}
