package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
    Optional<ConfirmationToken> findTopByUsuario_IdAndTypeOrderByExpiresAtDesc(Long id, TokenType type);
}
