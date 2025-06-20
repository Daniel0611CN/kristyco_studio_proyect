package org.iesvdm.proyecto_servidor.model.domain;

import org.iesvdm.proyecto_servidor.model.enums.EstadoPedido;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha = LocalDate.now();

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
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToMany
    @Builder.Default
    @ToString.Exclude
    private Set<Producto> productos = new HashSet<>();

}
