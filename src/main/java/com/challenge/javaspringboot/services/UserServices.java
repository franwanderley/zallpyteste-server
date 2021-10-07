package com.challenge.javaspringboot.services;

import java.util.List;
import java.util.Optional;

import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.domain.enums.TypeCharge;
import com.challenge.javaspringboot.repository.UserRepository;
import com.challenge.javaspringboot.security.UserSS;
import com.challenge.javaspringboot.services.exception.AuthorizationException;
import com.challenge.javaspringboot.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
   @Autowired
   public UserRepository userRepo;
   @Autowired
   public BCryptPasswordEncoder bcrypt;

   public static UserSS authenticated() {
      try{
         return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      }catch(Exception e){
         return null;
      }
   }

   public List<User> findAll(){
      return userRepo.findAll();
   }

   public User findById(Integer id){
      UserSS user = authenticated();
      if( (user == null || !user.hasRole(TypeCharge.ADMINISTRADOR)) && !id.equals(user.getId()) ){
         throw new AuthorizationException("Acesso Negado!");
      }
      Optional<User> obj = userRepo.findById(id);
      return obj.orElseThrow(() -> 
      new ObjectNotFoundException("objeto não encontrado "+ User.class.getName())
      );
   }

   public Integer insert(User user){
      user.setId(null);
      user.setPassword( bcrypt.encode(user.getPassword()) );
      User userResponse = userRepo.save(user);
      return userResponse.getId();
   }

   public void update(Integer id, User user){
      findById(id);
      user.setId(id);
      user.setPassword( bcrypt.encode(user.getPassword()) );
      userRepo.save(user);
   }

   public void delete(Integer id){
      User user = findById(id);
      userRepo.delete(user);
   }
   public User login(String email){
      Optional<User> obj = userRepo.findByEmail(email);
      return obj.orElseThrow();
   }
}
