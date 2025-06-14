package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.repository.ConfirmationTokenRepository;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.service.UsuarioService;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @GetMapping({"", "/"})
    public List<Usuario> all() { return this.usuarioService.all(); }

    @PostMapping({"", "/"})
    public Usuario newUsuario(@RequestBody Usuario usuario) {
        return this.usuarioService.saveOrGetIfExists(usuario);
    }

    @GetMapping("/{id}")
    public Usuario one(@PathVariable("id") Long id) {
        return this.usuarioService.one(id);
    }

    @PutMapping("/{id}")
    public Usuario replaceUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return this.usuarioService.replace(id, usuario);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        this.confirmationTokenRepository.deleteByUsuarioId(id);
        this.usuarioService.delete(id);
    }

    @PutMapping("/password")
    public ResponseEntity<?> changeOwnPassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Map<String, String> payload) {

        String username = userDetails.getUsername();
        String newPassword = payload.get("newPassword");

        if (newPassword == null || newPassword.isBlank() || newPassword.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("message", "La nueva contraseña es inválida."));
        }

        usuarioService.changePassword(username, newPassword);
        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente."));
    }

}
