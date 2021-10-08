package com.challenge.javaspringboot.services;

import java.util.List;
import java.util.Optional;

import com.challenge.javaspringboot.domain.Project;
import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.domain.enums.TypeCharge;
import com.challenge.javaspringboot.repository.ProjectRepository;
import com.challenge.javaspringboot.security.UserSS;
import com.challenge.javaspringboot.services.exception.AuthorizationException;
import com.challenge.javaspringboot.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
   
   @Autowired
   private ProjectRepository ProjectRepo;
   @Autowired
   private UserServices userService;

   public List<Project> findAll(){
      UserSS userSS = UserServices.authenticated();
      if(userSS == null){
         throw new AuthorizationException("Acesso Negado!");
      }
      if(userSS.hasRole(TypeCharge.ADMINISTRADOR)){
         return ProjectRepo.findAll();
      }
      User user = userService.findById(userSS.getId());

      return ProjectRepo.findByUsers(user);
   }

   public Project findById(Integer id){
      Optional<Project> obj = ProjectRepo.findById(id);
      return obj.orElseThrow(() -> 
      new ObjectNotFoundException("objeto não encontrado "+ Project.class.getName())
      );
   }

   public Integer insert(Project project){
      project.setId(null);
      Project projectSave = ProjectRepo.save(project);
      return projectSave.getId();
   }

   public void update(Integer id, Project project){
      findById(id);
      project.setId(id);
      ProjectRepo.save(project);
   }

   public void delete(Integer id){
      Project project = findById(id);
      ProjectRepo.delete(project);
   }
}
