    package org.iesvdm.proyecto_servidor.controller;

    import org.iesvdm.proyecto_servidor.dto.DTOPasswordValidation;
    import org.iesvdm.proyecto_servidor.service.ConfirmationTokenService;
    import org.iesvdm.proyecto_servidor.service.PasswordService;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.HttpStatus;
    import lombok.AllArgsConstructor;
    import java.util.HashMap;
    import java.util.Map;

    @RestController
    @AllArgsConstructor
    @RequestMapping("/api/v1/confirmation_token")
    @CrossOrigin(origins = "http://localhost:4200")
    //@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
    public class ConfirmationTokenController {

        private final ConfirmationTokenService confirmationTokenService;
        private final PasswordService passwordService;

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
                System.out.println(valid);
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

        @PutMapping("/request-reset-password")
        public ResponseEntity<Map<String, Object>> requestResetPassword(@RequestParam("email") String email) {
            return passwordService.requestReset(email);
        }

        @GetMapping("/validate-reset-token")
        public ResponseEntity<Map<String, Object>> validateResetToken(@RequestParam("token") String token) {
            return passwordService.validateResetToken(token);
        }

        @PutMapping("/reset-password")
        public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> data) {
            String token = data.get("token");
            String newPassword = data.get("password");
            return passwordService.resetPassword(token, newPassword);
        }

        @PostMapping("/validate-old-password")
        public ResponseEntity<?> validateOldPassword(
                @AuthenticationPrincipal UserDetails userDetails,
                @RequestBody Map<String, String> payload) {

            String username = userDetails.getUsername();
            String oldPassword = payload.get("oldPassword");

            boolean isValid = passwordService.isOldPasswordCorrect(username, oldPassword);

            if (isValid) {
                return ResponseEntity.ok(Map.of("valid", true));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("valid", false, "message", "Contraseña incorrecta"));
            }
        }

    }











