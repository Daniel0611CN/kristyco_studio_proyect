package org.iesvdm.proyecto_servidor.model.record.mail;

import java.util.Map;

public record MailHtmlData (
     String[] toUser,
     String subject,
     String templateName,
     Map<String, Object> variables
) {}
