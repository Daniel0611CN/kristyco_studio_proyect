package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.record.mail.MailData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailFileData;
import org.iesvdm.proyecto_servidor.service.MailServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailServiceInterface emailService;

    public MailController(MailServiceInterface emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-mail")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody MailData mailData) {

        System.out.println(new DTOMessageResponse(String.format("Mensaje recibido %s", mailData)));

        emailService.sendMail(mailData.toUser(), mailData.subject(), mailData.message());

        Map<String, DTOMessageResponse> response = new HashMap<>();
        response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-mail-file")
    public ResponseEntity<?> receiveRequestEmailWithFile(@RequestBody MailFileData mailFileData) {

        try {
            String fileName = mailFileData.file().getName();

            Path path = Paths.get(String.format("src/main/resources/files/%s", fileName));

            Files.createDirectories(path.getParent());
            Files.copy(mailFileData.file().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendMailWithFile(mailFileData.toUser(), mailFileData.subject(), mailFileData.message(), file);

            Map<String, DTOMessageResponse> response = new HashMap<>();
            response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));
            response.put("archivo", new DTOMessageResponse("FileName"));

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }

    @PostMapping("/send-html-mail")
    public ResponseEntity<?> receiveRequestHtmlEmail(@RequestBody MailData mailData) {

        System.out.println(new DTOMessageResponse(String.format("Mensaje recibido %s", mailData)));

        String templateName = "email";
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "Bienvenido, " + mailData.toUser()[0] + "!");
        variables.put("descripcion", mailData.message());

        emailService.sendHtmlMail(mailData.toUser(), mailData.subject(), templateName, variables);

        Map<String, DTOMessageResponse> response = new HashMap<>();
        response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));

        return ResponseEntity.ok(response);
    }

}
