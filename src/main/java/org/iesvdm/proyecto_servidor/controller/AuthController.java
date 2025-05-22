package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.model.enums.TokenType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.iesvdm.proyecto_servidor.service.ConfirmationTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.repository.RolRepository;
import org.iesvdm.proyecto_servidor.service.UserDetailsImpl;
import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.iesvdm.proyecto_servidor.mapper.MapStructMapper;
import org.springframework.security.core.GrantedAuthority;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.dto.form.DTORegister;
import org.springframework.security.core.Authentication;
import org.iesvdm.proyecto_servidor.security.TokenUtils;
import org.iesvdm.proyecto_servidor.model.enums.TipoRol;
import org.iesvdm.proyecto_servidor.service.MailService;
import org.iesvdm.proyecto_servidor.dto.form.DTOLogin;
import org.iesvdm.proyecto_servidor.model.domain.Rol;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final ConfirmationTokenService confirmationTokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private final MailController mailController;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final MailService mailService;
    private final TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody DTOLogin loginRequest) {
        try {
            Optional<Usuario> usuarioOpt = userRepository.findByNombre(loginRequest.getUsername());

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "No existe ninguna cuenta con este usuario."));
            }

            Usuario usuario = usuarioOpt.get();

            if (!encoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "El valor de la contraseña no es correcto, inténtelo de nuevo."));
            }

            if (!usuario.getEnabled()) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(Map.of("message", "Tu cuenta no está habilitada. Por favor, confirma tu correo electrónico."));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenUtils.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", userDetails.getId());
            response.put("username", userDetails.getUsername());
            response.put("enabled", userDetails.isEnabled());
            response.put("email", userDetails.getEmail());
            response.put("roles", roles);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Ha ocurrido un error inesperado."));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody DTORegister registerRequest) {
        if (userRepository.existsByNombre(registerRequest.getUsername()))
            return ResponseEntity.badRequest().body(new DTOMessageResponse("El usuario corresponde a una cuenta"));

        if (userRepository.existsByEmail(registerRequest.getEmail()))
            return ResponseEntity.badRequest().body(new DTOMessageResponse("El email corresponde a una cuenta"));

        if (userRepository.existsByTelefono(registerRequest.getTelefono()))
            return ResponseEntity.badRequest().body(new DTOMessageResponse("El teléfono corresponde a una cuenta"));

        Usuario user = mapStructMapper.registroToUsuario(registerRequest, encoder);

        Set<String> strRoles = registerRequest.getRoles();
        Set<Rol> roles = new HashSet<>();

        if (strRoles == null) {
            Rol userRole = rolRepository.findByRol(TipoRol.ROL_USER)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol de usuario"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin") || registerRequest.getEmail().equals("kristycostudio@gmail.com") || registerRequest.getEmail().equals("daniclavijonunez@gmail.com")) {
                    Rol adminRole = rolRepository.findByRol(TipoRol.ROL_ADMIN)
                            .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol de administrador"));
                    roles.add(adminRole);
                } else {
                    Rol userRole = rolRepository.findByRol(TipoRol.ROL_USER)
                            .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol de usuario"));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ConfirmationToken confirmationToken = confirmationTokenService.createForUsuario(user, Duration.ofMinutes(30), TokenType.REGISTER_CONFIRMATION);
        mailService.sendHtmlMail(mailService.buildMailHtmlData(user, confirmationToken.getToken()));

        return ResponseEntity.ok(new DTOMessageResponse(String.format("Usuario registrado correctamente, token=%s", confirmationToken.getToken())));
    }

}
