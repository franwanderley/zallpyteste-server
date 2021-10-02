package com.challenge.javaspringboot.services;

import java.util.List;
import java.util.Optional;

import com.challenge.javaspringboot.domain.Project;
import com.challenge.javaspringboot.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
   
   @Autowired
   private ProjectRepository ProjectRepo;

   public List<Project> findAll(){
      return ProjectRepo.findAll();
   }

   public Project findById(Integer id){
      Optional<Project> obj = ProjectRepo.findById(id);
      return obj.orElseThrow();
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
