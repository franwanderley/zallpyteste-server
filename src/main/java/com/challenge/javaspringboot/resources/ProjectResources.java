package com.challenge.javaspringboot.resources;

import java.net.URI;
import java.util.List;

import com.challenge.javaspringboot.domain.Project;
import com.challenge.javaspringboot.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "projects")
public class ProjectResources {
   @Autowired
   ProjectService projectService;

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Project>> findAll(){
      List<Project> projects = projectService.findAll();
      return ResponseEntity.ok().body(projects);
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Project> findById(@PathVariable Integer id){
      Project project = projectService.findById(id);
      return ResponseEntity.ok().body(project);
   }

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Project project){
      Integer id = projectService.insert(project);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
         .buildAndExpand(id).toUri();
      return ResponseEntity.created(uri).build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      projectService.delete(id);
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Project project){
      projectService.update(id, project);
      return ResponseEntity.noContent().build();
   }
}
