package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.model.record.mail.MailHtmlData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailFileData;
import org.iesvdm.proyecto_servidor.model.record.mail.MailData;
import org.iesvdm.proyecto_servidor.dto.MailHtmlDataVariables;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
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

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    public MailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

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

    @Override
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

            ClassPathResource image = new ClassPathResource("static/img/logo.png");
            helper.addInline("logo", image);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }

    @Override
    public MailHtmlData buildMailHtmlData(Usuario user, String token) {
        MailData mailData = getMailParameters(user, "Registro en KristyCoStudio", "mail");

        MailHtmlDataVariables mailHtmlDataVariables = getMailHtmlDataVariables("Bienvenido, %s!", user,
                "Gracias por registrarte en nuestra plataforma. !Nos alegra tenerte con nosotros!",
                "Para iniciar sesión pulsa en el siguiente enlace", "http://localhost:4200/confirmar-token/%s", token);

        Map<String, Object> variables = Map.of(
                "welcome", mailHtmlDataVariables.getWelcome(),
                "description", mailHtmlDataVariables.getDescription(),
                "link", mailHtmlDataVariables.getLink(),
                "token", mailHtmlDataVariables.getToken()
        );

        return new MailHtmlData(mailData.toUser(), mailData.subject(), mailData.message(), variables);
    }

    @Override
    public MailHtmlData buildResendHtmlData(Usuario user, String token) {
        MailData mailData = getMailParameters(user, "Reenvío de confirmación - KristyCoStudio", "resend-mail");

        MailHtmlDataVariables mailHtmlDataVariables = getMailHtmlDataVariables("Reenvío de correo de confirmación para %s!", user,
                "Has solicitado un nuevo enlace de confirmación. Usa el siguiente enlace para validar tu cuenta:", null, null, token);

        Map<String, Object> variables = Map.of(
                "welcome", mailHtmlDataVariables.getWelcome(),
                "description", mailHtmlDataVariables.getDescription(),
                "token", mailHtmlDataVariables.getToken()
        );

        return new MailHtmlData(mailData.toUser(), mailData.subject(), mailData.message(), variables);
    }

    @Override
    public MailHtmlData buildPasswordResetHtmlData(Usuario user, String token) {
        MailData mailData = getMailParameters(user, "Restablecer contraseña - KristyCoStudio", "reset-password");

        MailHtmlDataVariables mailHtmlDataVariables = getMailHtmlDataVariables(
                "Hola %s, ¿has olvidado tu contraseña?", user,
                "Hemos recibido una solicitud para restablecer tu contraseña. Si no fuiste tú, puedes ignorar este correo.",
                "Haz clic en el siguiente enlace para establecer una nueva contraseña:", "http://localhost:4200/reset-password/%s", token);

        Map<String, Object> variables = Map.of(
                "welcome", mailHtmlDataVariables.getWelcome(),
                "description", mailHtmlDataVariables.getDescription(),
                "link", mailHtmlDataVariables.getLink(),
                "token", mailHtmlDataVariables.getToken()
        );

        return new MailHtmlData(mailData.toUser(), mailData.subject(), mailData.message(), variables);
    }

    private static MailData getMailParameters(Usuario user, String subject, String template) {
        String[] users = new String[] { user.getEmail() };
        return new MailData(users, subject, template);
    }

    private static MailHtmlDataVariables getMailHtmlDataVariables(String welcome, Usuario user, String description, String link, String enlace, String token) {
        String confirmToken = String.format(enlace, token);
        return new MailHtmlDataVariables(String.format(welcome, user.getNombre()), description, link, confirmToken);
    }

}
