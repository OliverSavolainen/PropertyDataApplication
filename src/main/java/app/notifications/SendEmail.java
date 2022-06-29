package app.notifications;

import app.properties.RentApartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    private static final Logger log = LoggerFactory.getLogger(SendEmail.class);

    public void send(String from, String recipient, List<RentApartment> suitableApartments) {

        // Sender's email ID needs to be mentioned

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject("Suitable apartments according to your query!");

            StringBuilder fullText = new StringBuilder();
            for (RentApartment suitableApartment : suitableApartments) {
                fullText.append(suitableApartment.toString()).append("\n");
                fullText.append("URL: ").append(suitableApartment.getURL()).append("\n").append("\n");
            }

            // Now set the actual message
            message.setText(String.valueOf(fullText));

            // Send message
            Transport.send(message);
            log.info("Email sent");
        } catch (MessagingException mex) {
            log.error(mex.toString());
        }
    }
}
