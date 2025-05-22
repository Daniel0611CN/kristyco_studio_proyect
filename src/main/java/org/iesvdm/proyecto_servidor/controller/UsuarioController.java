package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

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
        this.usuarioService.delete(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/pswd")
    public void changePassword(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
//        String oldPswd = mapPasswords.get("oldPswd");
//        String newPswd = mapPasswords.get("newPswd");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (pswdEncoder.matches(oldPswd, usuarioService.oneEmail(auth.getName()).getPassword())) {
//            usuarioService.changePassword(auth.getName(), pswdEncoder.encode(newPswd));
//        }
//        else {
//            throw new AccessDeniedException("Contraseña incorrecta");
//        }
        usuarioService.changePassword(id, usuario.getPassword());
    }

}
