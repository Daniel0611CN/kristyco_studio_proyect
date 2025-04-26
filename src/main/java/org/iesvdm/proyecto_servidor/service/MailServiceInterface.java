package org.iesvdm.proyecto_servidor.service;

import java.io.File;
import java.util.Map;

public interface MailServiceInterface {

    void sendMail(String[] toUser, String subject, String message);

    void sendMailWithFile(String[] toUser, String subject, String message, File file);

    void sendHtmlMail(String[] toUser, String subject, String templateName, Map<String, Object> variables);

}
