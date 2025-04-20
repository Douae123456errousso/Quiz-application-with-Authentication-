import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.*;

public class EmailSender {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        String from = "votre-email@gmail.com";
        String password = "votre-mot-de-passe";
        String to = "destinataire@example.com";
        
        // Configurer les propriétés de la session
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Port SMTP pour STARTTLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Activer STARTTLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Spécifier TLS

        // Créer une session
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Créer un message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Email");
            message.setText("Ceci est un test.");

            // Envoyer le message
            Transport.send(message);
            System.out.println("Email envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
