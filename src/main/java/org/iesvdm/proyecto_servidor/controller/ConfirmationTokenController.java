package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.service.ConfirmationTokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
@RestController
@RequestMapping("/api/v1/confirmation_token")
@AllArgsConstructor
public class ConfirmationTokenController {

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/confirm-register")
    public ResponseEntity<Map<String, Object>> confirm(@RequestParam("token") String token) {
        boolean response = confirmationTokenService.confirmToken(token);
        Map<String, Object> responseBody = new HashMap<>();

        if (response) {
            responseBody.put("Message", "Cuenta confirmada correctamente.");
            responseBody.put("Redirect", true);
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.put("Message", "El token es inválido o ha expirado.");
            responseBody.put("Redirect", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
    }

    @PutMapping("/revalidate-token/{username}")
    public ResponseEntity<Map<String, Object>> revalidateToken(@PathVariable("username") String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean valid = confirmationTokenService.revalidateToken(username);
            if (valid) {
                response.put("message", "Correo de confirmación reenviado correctamente");
                return ResponseEntity.ok(response);
            }
        } catch (IllegalStateException ex) {
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("message", "Error inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "No se pudo reenviar el correo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}











