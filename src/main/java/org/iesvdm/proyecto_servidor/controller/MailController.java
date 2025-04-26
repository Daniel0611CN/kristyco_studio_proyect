package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.model.record.mail.MailFileData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailHtmlData;
import org.iesvdm.proyecto_servidor.service.MailServiceInterface;
import org.iesvdm.proyecto_servidor.model.record.mail.MailData;
import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.nio.file.StandardCopyOption;
import lombok.AllArgsConstructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

@RestController
@RequestMapping("/api/v1/mail")
@AllArgsConstructor
public class MailController {

    private final MailServiceInterface emailService;

    @PostMapping("/send-mail")
    public ResponseEntity<?> sendRequestEmail(@RequestBody MailData mailData) {

        System.out.println(new DTOMessageResponse(String.format("Mensaje recibido %s", mailData)));

        emailService.sendMail(mailData);

        Map<String, DTOMessageResponse> response = new HashMap<>();
        response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-mail-file")
    public ResponseEntity<?> sendRequestEmailWithFile(@RequestBody MailFileData mailFileData) {

        try {
            String fileName = mailFileData.file().getName();

            Path path = Paths.get(String.format("src/main/resources/files/%s", fileName));

            Files.createDirectories(path.getParent());
            Files.copy(mailFileData.file().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendMailWithFile(mailFileData);

            Map<String, DTOMessageResponse> response = new HashMap<>();
            response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));
            response.put("archivo", new DTOMessageResponse("FileName"));

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }

    @PostMapping("/send-html-mail")
    public ResponseEntity<?> sendRequestHtmlEmail(@RequestBody MailHtmlData mailHtmlData) {

        System.out.println(new DTOMessageResponse(String.format("Mensaje recibido %s", mailHtmlData)));

        emailService.sendHtmlMail(mailHtmlData);

        Map<String, DTOMessageResponse> response = new HashMap<>();
        response.put("estado", new DTOMessageResponse("Mensaje enviado correctamente"));

        return ResponseEntity.ok(response);
    }

}
