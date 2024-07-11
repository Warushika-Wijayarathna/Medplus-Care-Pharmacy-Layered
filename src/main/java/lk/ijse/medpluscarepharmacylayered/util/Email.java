package lk.ijse.medpluscarepharmacylayered.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Email {

    private static final String FROM_EMAIL = "www.thilankathushani@gmail.com";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SMTP_AUTH_USER = "www.thilankathushani@gmail.com";
    private static final String SMTP_AUTH_PASS = "dyjz rbny jhdf roxl";

    private Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PASS);
            }
        });
    }

    public void sendEmail(String recipientEmail, String recipientName, File attachment) {
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Your Test Results Are Back!");

            String emailContent = String.format(
                    "Dear %s,\n\nYour test results are ready. Please find the attached PDF file for your test results.\n\nThank you for choosing MedPlus Care Pharmacy.\n\nBest Regards,\nMedPlus Care Pharmacy",
                    recipientName
            );

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(emailContent);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(attachment);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully....");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCustomerEmail(String recipientEmail, String recipientName) {
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Welcome!");

            String emailContent = String.format(
                    "Dear %s,\n\nWelcome to MedPlus Care Pharmacy!\nWe are delighted to have you as a customer.\nThank you for choosing us.\n\nBest Regards,\nMedPlus Care Pharmacy\n\nThis is an auto-generated email. Please do not reply.",
                    recipientName
            );

            message.setText(emailContent);

            Transport.send(message);
            System.out.println("Welcome email sent successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
