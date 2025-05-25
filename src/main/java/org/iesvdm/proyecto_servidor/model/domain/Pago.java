package org.iesvdm.proyecto_servidor.model.domain;

import org.iesvdm.proyecto_servidor.model.enums.EstadoPago;
import org.iesvdm.proyecto_servidor.model.enums.MetodoPago;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private Pedido pedido;

}
