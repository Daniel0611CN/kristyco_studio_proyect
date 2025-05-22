package org.iesvdm.proyecto_servidor.security.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.enums.TokenType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
