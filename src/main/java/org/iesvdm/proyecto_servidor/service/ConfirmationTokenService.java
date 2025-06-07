package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.repository.ConfirmationTokenRepository;
import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final MailService mailService;

    public Optional<ConfirmationToken> getToken(String token) { return confirmationTokenRepository.findByToken(token); }

    private void throwIf(boolean condition, String message) { if (condition) throw new IllegalStateException(message); }

    @Transactional
    public ConfirmationToken createForUsuario(Usuario usuario, Duration duration, TokenType type) {
        confirmationTokenRepository.findTopByUsuario_IdAndTypeOrderByExpiresAtDesc(usuario.getId(), type)
                .ifPresent(confirmationTokenRepository::delete);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plus(duration), type, usuario);
        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken;
    }

    @Transactional
    public boolean confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token).orElseThrow(() -> new IllegalStateException("El token no ha sido encontrado"));

        throwIf(confirmationToken.getConfirmedAt() != null, "El email ya ha sido confirmado");
        throwIf(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()), "El token ha expirado");

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

        usuarioService.enableUsuario(confirmationToken.getUsuario().getEmail());

        return true;
    }

    @Transactional
    public boolean revalidateToken(String username) {
        Optional<Usuario> usuarioOpt =  usuarioRepository.findByNombre(username);
        throwIf(usuarioOpt.isEmpty(), "Usuario no encontrado");
        Usuario usuario = usuarioOpt.get();

        ConfirmationToken oldToken = confirmationTokenRepository.findTopByUsuario_IdAndTypeOrderByExpiresAtDesc(usuario.getId(), TokenType.REGISTER_CONFIRMATION)
                .orElseThrow(() -> new IllegalStateException("Token no encontrado para el usuario"));

        throwIf(oldToken.getConfirmedAt() != null, "El email ha sido confirmado anteriormente");
        throwIf(oldToken.getExpiresAt().isAfter(LocalDateTime.now()), "El token todavía no ha expirado");
        confirmationTokenRepository.delete(oldToken);

        ConfirmationToken newToken = createForUsuario(usuario, Duration.ofMinutes(120), TokenType.REVALIDATE_TOKEN);

        mailService.sendHtmlMail(mailService.buildResendHtmlData(usuario, newToken.getToken()));

        return true;
    }

    public ConfirmationToken validateTokenOrThrow(String token, TokenType expectedType) {
        ConfirmationToken confirmationToken = getToken(token).orElseThrow(() -> new IllegalStateException("Token no válido"));

        throwIf(!confirmationToken.getType().equals(expectedType), "Tipo de token no válido");
        throwIf(confirmationToken.getConfirmedAt() != null, "El token ya ha sido usado");
        throwIf(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()), "El token ha expirado");

        return confirmationToken;
    }

    @Transactional
    public void markAsUsed(ConfirmationToken token) {
        token.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(token);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(String message, Map<String, Object> extraData) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", new DTOMessageResponse(message));
        if (extraData != null) response.putAll(extraData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Usuario getUserByResetToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido o expirado"));

        return confirmationToken.getUsuario();
    }

}
