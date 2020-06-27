package kunzou.me.codingPractice.service;

import kunzou.me.codingPractice.dto.EmailDetail;

import javax.mail.MessagingException;

public interface EmailService {
  void sendEmail(EmailDetail emailDetail) throws MessagingException;
}
