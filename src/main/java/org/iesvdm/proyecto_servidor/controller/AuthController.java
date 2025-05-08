package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import org.iesvdm.proyecto_servidor.dto.MailHtmlDataVariables;
import org.iesvdm.proyecto_servidor.mapper.MapStructMapper;
import org.iesvdm.proyecto_servidor.model.record.mail.MailHtmlData;
import org.iesvdm.proyecto_servidor.security.token.ConfirmationToken;
import org.iesvdm.proyecto_servidor.service.ConfirmationTokenService;
import org.iesvdm.proyecto_servidor.service.MailService;
import org.iesvdm.proyecto_servidor.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.repository.RolRepository;
import org.iesvdm.proyecto_servidor.service.UserDetailsImpl;
import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.security.core.Authentication;
import org.iesvdm.proyecto_servidor.security.TokenUtils;
import org.iesvdm.proyecto_servidor.model.enums.TipoRol;
import org.iesvdm.proyecto_servidor.model.domain.Rol;
import org.iesvdm.proyecto_servidor.dto.form.DTORegister;
import org.iesvdm.proyecto_servidor.dto.form.DTOLogin;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService tokenService;
    private final UsuarioRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private final MailController mailController;
    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody DTOLogin loginRequest) {

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
        response.put("email", userDetails.getEmail());
        response.put("roles", roles);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody DTORegister registerRequest) {
        if (userRepository.existsByNombre(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new DTOMessageResponse("Error: El Usuario introducido ya existe"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new DTOMessageResponse("Error: El Email introducido ya existe"));
        }

        Usuario user = mapStructMapper.registroToUsuario(registerRequest, encoder);

        Set<String> strRoles = registerRequest.getRoles();
        Set<Rol> roles = new HashSet<>();

        if (strRoles == null) {
            Rol userRole = rolRepository.findByRol(TipoRol.ROL_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Rol adminRole = rolRepository.findByRol(TipoRol.ROL_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(adminRole);

                        break;

                    default:
                        Rol userRole = rolRepository.findByRol(TipoRol.ROL_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
        );

        tokenService.saveConfirmationToken(confirmationToken);

        mailController.sendRequestHtmlEmail(getMailHtmlData(user, token));

        return ResponseEntity.ok(new DTOMessageResponse(String.format("Usuario registrado correctamente %s", token)));
    }

    private static MailHtmlData getMailHtmlData(Usuario user, String token) {

        String[] users = new String[] { user.getEmail() };
        String subject = "Registro en KristyCoStudio";
        String template = "mail";

        MailHtmlDataVariables mailHtmlDataVariables = new MailHtmlDataVariables(
                String.format("Bienvenido, %s!", user.getNombre()),
                String.format("Bienvenido, %s", user.getNombre()),
                "Gracias por registrarte en nuestra plataforma. ¡Nos alegra tenerte con nosotros!",
                "Pulsa en el siguiente enlace para iniciar sesión con tu cuenta: ",
                token
        );

        Map<String, Object> variables = Map.of(
                "title", mailHtmlDataVariables.getTitle(),
                "bienvenida", mailHtmlDataVariables.getBienvenida(),
                "descripcion", mailHtmlDataVariables.getDescripcion(),
                "enlace", mailHtmlDataVariables.getLink(),
                "token", mailHtmlDataVariables.getToken()
        );

        return new MailHtmlData(users, subject, template, variables);
    }

    @GetMapping("/confirm-register")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        usuarioService.confirmToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:4200/login"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
