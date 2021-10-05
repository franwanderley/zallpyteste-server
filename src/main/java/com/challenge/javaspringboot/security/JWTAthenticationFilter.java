package com.challenge.javaspringboot.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.challenge.javaspringboot.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAthenticationFilter extends UsernamePasswordAuthenticationFilter {
   private JWTUtil jwtUtil;
   private AuthenticationManager authenticationManager;

   public JWTAthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
      this.jwtUtil = jwtUtil;
      this.authenticationManager = authenticationManager;
   }

   @Override
   public Authentication attemptAuthentication(
      HttpServletRequest req, HttpServletResponse res
   ) throws AuthenticationException{
      try {
         User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
         UsernamePasswordAuthenticationToken authToken
            = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
         Authentication auth = authenticationManager.authenticate(authToken);
         return auth;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   
   protected void successfulAuthentication(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth
   ) throws IOException, ServletException{
      String username = ((UserSS) auth.getPrincipal()).getUsername();
      String token = jwtUtil.generateToken(username);
      res.addHeader("Authorization", "Bearer " + token);
   }
}
