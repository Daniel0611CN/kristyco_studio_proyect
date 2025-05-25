package org.iesvdm.proyecto_servidor.security.token;

import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt = null;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, TokenType type, Usuario usuario) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.type = type;
        this.usuario = usuario;
    }
}
