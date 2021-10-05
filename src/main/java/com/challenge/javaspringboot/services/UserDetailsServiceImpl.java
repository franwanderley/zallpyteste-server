package com.challenge.javaspringboot.services;

import java.util.Arrays;
import java.util.Optional;

import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.repository.UserRepository;
import com.challenge.javaspringboot.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private UserRepository repo;

   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
      Optional<User> obj = repo.findByEmail(email);
      User user = obj.orElseThrow(() -> 
         new UsernameNotFoundException("email " +email+ "não encontrado!")
      );

      return new UserSS(
         user.getId(), 
         user.getEmail(), 
         user.getPassword(),
         Arrays.asList((new SimpleGrantedAuthority(user.getCharge().toString())))
      );   
   }
}
