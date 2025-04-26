package org.iesvdm.proyecto_servidor.model.record.mail;

public record MailData (
     String[] toUser,
     String subject,
     String message
) {}
