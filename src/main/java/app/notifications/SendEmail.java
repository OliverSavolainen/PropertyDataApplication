package app.notifications;

import app.exceptions.SendingFailedException;
import app.filters.FilterLogic;
import app.filters.OverallFilter;
import app.properties.RentApartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Class for sending an email notification of apartments that are suitable through the filter
 */
public class SendEmail {
    private static final Logger log = LoggerFactory.getLogger(SendEmail.class);
    private final String from;
    private final String recipient;
    private final String password;
    private final OverallFilter overallFilter;

    public SendEmail(String from, String recipient, String password, OverallFilter overallFilter) {
        this.from = from;
        this.recipient = recipient;
        this.password = password;
        this.overallFilter = overallFilter;
    }

    /**
     * Method for sending the email, currently by gmail
     * @param apartments list of apartments that are filtered
     * @return all suitable apartments
     */
    public List<RentApartment> sendNotification(List<RentApartment> apartments) throws Exception {
        FilterLogic filterLogic = new FilterLogic(overallFilter, apartments);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Here are the apartments your filter found!");

            StringBuilder fullText = new StringBuilder();
            List<RentApartment> suitableApartments = filterLogic.filterAll();
            for (RentApartment suitableApartment : suitableApartments) {
                fullText.append(suitableApartment.toString()).append("\n");
                fullText.append("URL: ").append(suitableApartment.getURL()).append("\n").append("\n");
            }
            message.setText(String.valueOf(fullText));

            Transport.send(message);
            log.info("Email sent");
            return suitableApartments;
        } catch (MessagingException mex) {
            log.error(mex.toString());
            throw new SendingFailedException();
        }
    }
}
