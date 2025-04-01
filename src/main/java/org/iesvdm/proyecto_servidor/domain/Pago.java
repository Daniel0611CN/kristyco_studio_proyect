package org.iesvdm.proyecto_servidor.domain;

import org.iesvdm.proyecto_servidor.enums.EstadoPago;
import org.iesvdm.proyecto_servidor.enums.MetodoPago;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
//@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(scale = 4, nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPago metodo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    @OneToOne
    //@JsonIgnore
    private Pedido pedido;

}
