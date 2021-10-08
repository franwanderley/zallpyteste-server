package com.challenge.javaspringboot.services;

import java.util.List;
import java.util.Optional;

import com.challenge.javaspringboot.domain.HoursWork;
import com.challenge.javaspringboot.domain.enums.TypeCharge;
import com.challenge.javaspringboot.repository.HoursWorkRepository;
import com.challenge.javaspringboot.security.UserSS;
import com.challenge.javaspringboot.services.exception.AuthorizationException;
import com.challenge.javaspringboot.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoursWorkService {
   @Autowired
   private HoursWorkRepository repo;
   
   public List<HoursWork> findAll() {
      return repo.findAll();
   }

   public Integer insert(HoursWork hours){
      hours.setId(null);
      UserSS user = UserServices.authenticated();
      if(user.getId() != hours.getUser().getId())
         throw new AuthorizationException("Acesso Invalido!");

      HoursWork hoursCreated = repo.save(hours);
      return hoursCreated.getId();
   }

   public void update(Integer id,HoursWork hours){
      hours.setId(id);
      findById(id);
      repo.save(hours);
   }

   public HoursWork findById(Integer id) {
      Optional<HoursWork> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("objeto não encontrado "+ HoursWork.class.getName())
      );
   }

   public void delete(Integer id){
      HoursWork hours = findById(id);
      repo.delete(hours);
   }

   public List<HoursWork> findByUserAndProject(Integer idProject){
      UserSS user = UserServices.authenticated();
      if(user.hasRole(TypeCharge.ADMINISTRADOR)){
         return repo.findByProject(idProject);
      }

      return repo.findByUserAndProject(idProject, user.getId());
   }
}
