package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.Optional;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class PasswordService {

    private final ConfirmationTokenService tokenService;
    private final UsuarioService usuarioService;
    private final MailService mailService;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, Object>> requestReset(String email) {
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(email);
        if (usuarioOpt.isEmpty()) throw new IllegalStateException("No existe un usuario con ese email");
        Usuario usuario = usuarioOpt.get();

        ConfirmationToken token = tokenService.createForUsuario(usuario, Duration.ofMinutes(30), TokenType.RESET_PASSWORD);
        mailService.sendHtmlMail(mailService.buildPasswordResetHtmlData(usuario, token.getToken()));

        Map<String, Object> response = new HashMap<>();
        response.put("message", new DTOMessageResponse("Se ha enviado el enlace de restablecimiento al correo."));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> validateResetToken(String token) {
        tokenService.validateTokenOrThrow(token, TokenType.RESET_PASSWORD);

        Map<String, Object> response = new HashMap<>();
        response.put("message", new DTOMessageResponse("Token válido. Puede introducir nueva contraseña."));
        response.put("valid", true);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> resetPassword(String token, String newPassword) {
        ConfirmationToken tokenEntity = tokenService.validateTokenOrThrow(token, TokenType.RESET_PASSWORD);
        usuarioService.changePassword(tokenEntity.getUsuario().getId(), newPassword);
        tokenService.markAsUsed(tokenEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("message", new DTOMessageResponse("Contraseña actualizada correctamente."));
        return ResponseEntity.ok(response);
    }

    public boolean isOldPasswordCorrect(String token, String oldPassword) {
        Usuario usuario = confirmationTokenService.getUserByResetToken(token);
        return passwordEncoder.matches(oldPassword, usuario.getPassword());
    }

}
