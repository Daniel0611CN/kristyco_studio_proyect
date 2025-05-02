package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.model.record.mail.MailData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailFileData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailHtmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;
import java.util.Map;
import java.io.File;

@Service
public class MailService implements MailServiceInterface {

    @Value("${email.sender}")
    private String emailUser;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(MailData mailData) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailUser);
        mailMessage.setTo(mailData.toUser());
        mailMessage.setSubject(mailData.subject());
        mailMessage.setText(mailData.message());

        mailSender.send(mailMessage);
    }

    @Override
    public void sendMailWithFile(MailFileData mailFileData) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(mailFileData.toUser());
            mimeMessageHelper.setSubject(mailFileData.subject());
            mimeMessageHelper.setText(mailFileData.message());
            mimeMessageHelper.addAttachment(mailFileData.file().getName(), mailFileData.file());

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }

    public void sendHtmlMail(MailHtmlData mailHtmlData) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mailHtmlData.variables());

            String htmlContent = templateEngine.process(mailHtmlData.templateName(), context);

            helper.setFrom(emailUser);
            helper.setTo(mailHtmlData.toUser());
            helper.setSubject(mailHtmlData.subject());
            helper.setText(htmlContent, true);

            File imageFile = new File("src/main/resources/static/img/logo.png");
            helper.addInline("logo", imageFile);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }

}
