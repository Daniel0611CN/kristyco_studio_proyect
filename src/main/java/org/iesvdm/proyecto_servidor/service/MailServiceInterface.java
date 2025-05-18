package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.iesvdm.proyecto_servidor.model.record.mail.MailData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailFileData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailHtmlData;

public interface MailServiceInterface {

    void sendMail(MailData mailData);

    void sendMailWithFile(MailFileData mailFileData);

    void sendHtmlMail(MailHtmlData mailHtmlData);

    MailHtmlData buildMailHtmlData(Usuario user, String token);

    MailHtmlData buildResendHtmlData(Usuario user, String token);

}
