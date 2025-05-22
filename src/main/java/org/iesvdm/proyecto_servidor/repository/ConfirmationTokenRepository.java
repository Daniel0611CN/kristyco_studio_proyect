package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);

    Optional<ConfirmationToken> findTopByUsuario_IdOrderByExpiresAtDesc(Long usuarioId);

    Optional<ConfirmationToken> findTopByUsuario_IdAndTypeOrderByExpiresAtDesc(Long id, TokenType type);


}
