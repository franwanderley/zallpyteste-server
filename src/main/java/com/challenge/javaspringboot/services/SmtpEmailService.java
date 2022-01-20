package com.challenge.javaspringboot.services;


import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.security.JWTUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SmtpEmailService{
   @Autowired
   private TemplateEngine templateEngine;
   @Autowired
   private JavaMailSender javaMailSender;
   @Autowired
   private JWTUtil jwtUtil;
   private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class); 
   
   @Value("${default.sender}")
   private String sendEmail;
   @Value("${default.url.client}")
   private String urlCliente;

   void sendNewPasswordHtmlEmail(User user){
      String token = jwtUtil.generateToken(user.getEmail());
      try{
         MimeMessage mm = prepareMimeMessageFromNewPassword(user, token);
         sendHtmlEmail(mm);
      }catch(Exception e){
         LOG.error("Erro ao enviar email", e);
      }
   }

   void sendHtmlEmail(MimeMessage msg){
      LOG.info("sendHtmlEmail...");
      javaMailSender.send(msg);
      LOG.info("Email Html enviado!");
   }

   private MimeMessage prepareMimeMessageFromNewPassword(User user, String token) throws MessagingException {
      MimeMessage mm = javaMailSender.createMimeMessage();
      MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
      mmh.setTo(user.getEmail());
      mmh.setFrom(sendEmail);
      mmh.setSubject("Aqui está o link para criar sua nova senha - Zallpy");
      mmh.setSentDate(new Date(System.currentTimeMillis()));
      mmh.setText( htmlFromTemplateNewPassword(user, token), true);
      return mm;
   }

   private String htmlFromTemplateNewPassword(User user, String token){
      Context context = new Context();
      String url = urlCliente + token;
      context.setVariable("url", url);
      context.setVariable("name", user.getName());
      return templateEngine.process("email/NewPassword", context);
   }
   
}
