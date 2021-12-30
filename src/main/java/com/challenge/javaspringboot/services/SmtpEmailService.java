package com.challenge.javaspringboot.services;


import java.util.Date;

import com.challenge.javaspringboot.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService{
   @Autowired
   private MailSender mailSender;
   private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class); 
   
   @Value("${default.sender}")
   private String sendEmail;

   public void sendConfirmationNewPassword(User user){
      SimpleMailMessage sm = prepareSimpleMailMessageFromUser(user);
      sendEmail(sm);
   }

   public SimpleMailMessage prepareSimpleMailMessageFromUser(User user) {
      SimpleMailMessage sm = new SimpleMailMessage();
      sm.setTo(user.getEmail());
      sm.setFrom(sendEmail);
      sm.setSubject("Test forgot you password!");
      sm.setSentDate(new Date(System.currentTimeMillis()));
      sm.setText("Eh isso mesmo, se não fosse não seria.");
      return sm;
   }

   public void sendEmail(SimpleMailMessage msg) {
      LOG.info("Sending email ...");
      mailSender.send(msg);
      LOG.info("Email send success!");
      
   }
   
}
