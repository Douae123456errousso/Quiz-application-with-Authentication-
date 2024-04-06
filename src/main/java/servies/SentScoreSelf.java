package servies;

import dao.UserDAO;
import views.LoginView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class SentScoreSelf {
     public SentScoreSelf(String email) {

        // Recipient's email ID needs to be mentioned.

// Sender's email ID needs to be mentioned
        String from = "jeetkoursuri29@gmail.com";

        String host = "smtp.gmail.com";

// Get system properties
        Properties properties = System.getProperties();

// Setup mail server

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        final String password = "iqpkegtrsxpwglzy";
// Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties,new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new  PasswordAuthentication(from , password) ;
            }
        });

// Used to debug SMTP issues
        session.setDebug(true);

        try {

// Create a default MimeMessage object.

            MimeMessage message = new MimeMessage(session);

// Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

// Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

// Set Subject: header field
            message.setSubject("Quiz Game Score");

// Now set the actual message
            message.setText(UserDAO.TotalScore(email));

            System.out.println("sending...");

// Send message

            Transport.send(message);

            System.out.println("Detail successfully....");
            new LoginView(email);


        } catch (MessagingException mex) { mex.printStackTrace();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
