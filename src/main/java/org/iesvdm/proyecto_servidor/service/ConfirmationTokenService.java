package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.repository.ConfirmationTokenRepository;
import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.controller.MailController;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final MailController mailController;
    private final MailService mailService;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public boolean confirmToken(String token) {
        boolean isConfirmed = false;

        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("El token no ha sido encontrado"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("El email ha sido confirmado anteriormente");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("El token ha expirado");
        }

        setConfirmedAt(token);
        usuarioService.enableUsuario(confirmationToken.getUsuario().getEmail());

        isConfirmed = true;

        return isConfirmed;
    }

    Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByNombre(username);
    }

    public boolean revalidateToken(String username) {
        Optional<Usuario> usuarioOpt = findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalStateException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        ConfirmationToken oldToken = confirmationTokenRepository.findTopByUsuario_IdOrderByExpiresAtDesc(usuario.getId())
                .orElseThrow(() -> new IllegalStateException("Token no encontrado para el usuario"));

        if (oldToken.getConfirmedAt() != null) {
            throw new IllegalStateException("El email ya ha sido confirmado");
        }

        if (oldToken.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("El token todavía no ha expirado");
        }

        confirmationTokenRepository.delete(oldToken);

        String newTokenString = generateConfirmationToken();

        ConfirmationToken newToken = new ConfirmationToken(newTokenString, LocalDateTime.now(), LocalDateTime.now().plusMinutes(120), usuario);
        confirmationTokenRepository.save(newToken);

        usuarioService.enableUsuario(usuario.getEmail());
        mailController.sendRequestHtmlEmail(mailService.buildResendHtmlData(usuario, newTokenString));

        return true;
    }



}
