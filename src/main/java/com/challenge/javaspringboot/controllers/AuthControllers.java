package com.challenge.javaspringboot.controllers;

import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="auth")
public class AuthControllers {
   @Autowired
   private AuthService service;

   @RequestMapping(value="/forgot-password", method=RequestMethod.POST)
   public ResponseEntity<Void> sendEmail(@RequestBody User user){
      service.sendNewPassword(user.getEmail());
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value="/new-password/{token}", method=RequestMethod.POST)
   public ResponseEntity<Void> newPassword(@RequestBody String password, @PathVariable String token) {
      service.saveNewPassword(password, token);
      return ResponseEntity.noContent().build();
   }
}
