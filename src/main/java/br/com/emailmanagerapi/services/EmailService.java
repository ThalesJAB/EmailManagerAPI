package br.com.emailmanagerapi.services;

import br.com.emailmanagerapi.entities.EmailConfig;
import br.com.emailmanagerapi.entities.EmailMessage;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.eclipse.angus.mail.imap.IMAPFolder;
//import org.springframework.mail.SimpleMailMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service
public class EmailService {
    public void sendSimpleMessage(EmailConfig emailConfig, String to, String subject, String text, String displayName) {
        try {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(emailConfig.getSmtpHost());
        emailSender.setPort(emailConfig.getSmtpPort());
        emailSender.setUsername(emailConfig.getUsername());
        emailSender.setPassword(emailConfig.getPassword());

        Properties props = emailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        if (emailConfig.isSmtpStartTlsEnable()) {
            props.put("mail.smtp.starttls.enable", "true");
        }
        if (emailConfig.isSmtpSslEnable()) {
            props.put("mail.smtp.ssl.enable", "true");
        }
        props.put("mail.debug", "true");
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(emailConfig.getUsername());
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);


            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            if (displayName == null || displayName.isEmpty()) {
                helper.setFrom(emailConfig.getUsername());
            } else {
                helper.setFrom(new InternetAddress(emailConfig.getUsername(), displayName, "UTF-8"));
            }
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

        emailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public List<EmailMessage> readEmails(EmailConfig emailConfig, String folder) {
        List<EmailMessage> emails = new ArrayList<>();
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");

            store.connect(emailConfig.getImapHost(), emailConfig.getImapPort(), emailConfig.getUsername(), emailConfig.getPassword());
            IMAPFolder folderImap = (IMAPFolder) store.getFolder(folder);
            folderImap.open(Folder.READ_ONLY);
            Message[] messages = folderImap.getMessages();
            for (Message message : messages) {
                MimeMessage mimeMessage = (MimeMessage) message;

                // Extrair informações da mensagem
                String messageId = mimeMessage.getMessageID();
                String subject = mimeMessage.getSubject();
                String from = mimeMessage.getFrom()[0].toString();
                String sentDate = mimeMessage.getSentDate().toString();
                String htmlContent = getHtmlFromMessage(mimeMessage);
                String messageFormat = getBodyFromMessage(mimeMessage);

                boolean isReply = false;
                String inReplyTo = null;

                if (mimeMessage.getHeader("In-Reply-To") != null) {
                    isReply = true;
                    inReplyTo = mimeMessage.getHeader("In-Reply-To")[0];
                }
                EmailMessage emailMessage = new EmailMessage(messageId, subject, from, sentDate, htmlContent, messageFormat, isReply, inReplyTo);
                emails.add(emailMessage);
            }
            folderImap.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emails;
    }

    private String getHtmlFromMessage(MimeMessage message) throws Exception {
        if (message.isMimeType("text/html")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            return getHtmlFromMultipart((Multipart) message.getContent());
        }
        return "";
    }

    private String getHtmlFromMultipart(Multipart multipart) throws Exception {
        StringBuilder htmlContent = new StringBuilder();
        String plainTextContent = "";

        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bodyPart = multipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/html")) {
                htmlContent.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/plain")) {
                // Armazena o conteúdo plain text como fallback
                plainTextContent = bodyPart.getContent().toString();
            } else if (bodyPart.isMimeType("multipart/*")) {
                String result = getHtmlFromMultipart((Multipart) bodyPart.getContent());
                if (!result.isEmpty()) {
                    htmlContent.append(result);
                }
            }
        }

        // Se nenhum conteúdo HTML for encontrado, retorna o conteúdo plain text
        if (htmlContent.length() == 0 && !plainTextContent.isEmpty()) {
            return plainTextContent;
        }

        return htmlContent.toString();
    }


    public String getBodyFromMessage(MimeMessage message) throws Exception {
        Object content = message.getContent();

        if (content instanceof Multipart) {
            return getBodyFromMultipart((Multipart) content);
        } else if (content instanceof String) {
            return convertHtmlToText((String) content);
        }

        return "";
    }

    private String getBodyFromMultipart(Multipart multipart) throws Exception {
        StringBuilder bodyContent = new StringBuilder();

        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bodyPart = multipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                bodyContent.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                bodyContent.append(convertHtmlToText(bodyPart.getContent().toString()));
            } else if (bodyPart.isMimeType("multipart/*")) {
                String result = getBodyFromMultipart((Multipart) bodyPart.getContent());
                if (!result.isEmpty()) {
                    bodyContent.append(result);
                }
            }
        }
        return bodyContent.toString();
    }

    private String convertHtmlToText(String html) {
        Document document = Jsoup.parse(html);
        return document.text();
    }

//    private static String detectCharset(byte[] bytes) {
//        UniversalDetector detector = new UniversalDetector(null);
//        detector.handleData(bytes, 0, bytes.length);
//        detector.dataEnd();
//        return detector.getDetectedCharset();
//    }
}
