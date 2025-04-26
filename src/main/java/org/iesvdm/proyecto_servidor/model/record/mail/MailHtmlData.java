package org.iesvdm.proyecto_servidor.model.record.mail;

import org.iesvdm.proyecto_servidor.dto.MailHtmlDataVariables;

import java.util.Map;

public record MailHtmlData (
     String[] toUser,
     String subject,
     String templateName,
     Map<String, Object> variables
) {}
