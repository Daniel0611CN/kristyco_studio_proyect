package org.iesvdm.proyecto_servidor.model.record.mail;

import org.springframework.web.multipart.MultipartFile;

public record MailFileData (
        String[] toUser,
        String subject,
        String message,
        MultipartFile file
) {}
