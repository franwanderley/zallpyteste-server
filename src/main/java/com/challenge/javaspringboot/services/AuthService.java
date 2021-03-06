package com.challenge.javaspringboot.services;

import java.util.Optional;
import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.dto.NewPasswordDTO;
import com.challenge.javaspringboot.repository.UserRepository;
import com.challenge.javaspringboot.security.JWTUtil;
import com.challenge.javaspringboot.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
   @Autowired
   private UserRepository repo;
   @Autowired
   private BCryptPasswordEncoder bcrypt;
   @Autowired
   private JWTUtil jwtUtil;
   @Autowired
   private SmtpEmailService mail;

   public void sendNewPassword(String email){
      Optional<User> obj = repo.findByEmail(email);
      System.out.println("Email send "+ email);
      User user = obj.orElseThrow(() -> 
      new ObjectNotFoundException("email " +email+ " não encontrado!")
      );
      mail.sendNewPasswordHtmlEmail(user);

   }

   public void saveNewPassword(NewPasswordDTO newPassword, String token){
      String passCript = bcrypt.encode(newPassword.getPassword());
      if(jwtUtil.tokenValidate(token)){
         String email = jwtUtil.getUsername(token);
         System.out.println("Email new password " + newPassword.getPassword());
         Optional<User> obj = repo.findByEmail(email);
         User user = obj.orElseThrow(() -> 
            new ObjectNotFoundException("email " + email + " não encontrado!")
         );
         user.setPassword(passCript);
         repo.save(user);
      } else
         new ObjectNotFoundException("token " + token + "não encontrado!");

   }
   
}
