package kunzou.me.codingPractice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
  private MongoTemplate mongoTemplate;
  private static final String EMAIL_SUBJECT = "New appointment: %s to %s, %s";
  private static final String GUEST_EMAIL_BODY =
      "Hello %s,\n" +
          "\t\tYour appointment with %s at %s is pending. You will be notified once it is approved. Thank you.\n\t" +
          "To review your appoint, please visit %s";

  private static final String USER_EMAIL_BODY = "Guest name:\t %s\nEmail:\t\t %s\nMessage:\t %s\nTo approve or decline this appointment, please visit: %s";

  public EmailServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

/*  public void sendEmail(EmailDetail emailDetail) throws MessagingException {
    Transport.send(createMessage(emailDetail));
    logger.info("Email sent: "+emailDetail.toString());
  }

  private Message createMessage(EmailDetail emailDetail) throws MessagingException {
    final String username = System.getenv("EMAIL_USERNAME");
    final String password = System.getenv("EMAIL_PASSWORD");

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.live.com");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });
    session.setDebug(true);
    Message message = new MimeMessage(session);
    message.setFrom(new
      InternetAddress(System.getenv("EMAIL_USERNAME")));
    message.setRecipients(Message.RecipientType.TO,
      InternetAddress.parse(System.getenv("EMAIL_RECIPIENT")));
    message.setSubject("咨询"+emailDetail.getAddress());
    message.setText(
      "咨询人:"+emailDetail.getFrom()+"\n"+
        "邮箱:"+emailDetail.getEmail()+"\n"+
        "电话:"+emailDetail.getPhoneNumber()+"\n"+
        "信息:"+emailDetail.getMessage()
    );

    return message;
  }*/

}
