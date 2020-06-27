package kunzou.me.codingPractice.service;

import kunzou.me.codingPractice.dto.EmailDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
  private static final String EMAIL_SUBJECT = "有人要买 %s";
  private static final String EMAIL_BODY ="咨询人: %s\n邮箱: %s\n电话: %s\n信息: %s\n";

  @Override
  public void sendEmail(EmailDetail emailDetail) throws MessagingException {
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
    if(StringUtils.isNoneBlank(System.getenv("EMAIL_CC"))) {
      message.setRecipients(Message.RecipientType.CC,
        InternetAddress.parse(System.getenv("EMAIL_CC")));
    }
    message.setSubject(String.format(EMAIL_SUBJECT,emailDetail.getProduct()));
    message.setText(String.format(EMAIL_BODY,
      emailDetail.getFrom(), emailDetail.getEmail(), emailDetail.getPhoneNumber(), emailDetail.getMessage()));

    return message;
  }

}
