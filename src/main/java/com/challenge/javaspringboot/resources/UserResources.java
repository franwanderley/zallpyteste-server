package com.challenge.javaspringboot.resources;

import java.net.URI;
import java.util.List;

import com.challenge.javaspringboot.domain.User;
import com.challenge.javaspringboot.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "users")
public class UserResources {
   @Autowired
   public UserServices userServices;

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<User>> findAll(){
      List<User> users = userServices.findAll();
      return ResponseEntity.ok().body(users);
   }

   @RequestMapping(value = "/{id}",method = RequestMethod.GET)
   public ResponseEntity<User> findById(@PathVariable Integer id){
      User user = userServices.findById(id);
      return ResponseEntity.ok().body(user);
   }

   @RequestMapping(method=RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody User user) {
      Integer id = userServices.insert(user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
         .buildAndExpand(id).toUri();
      return ResponseEntity.created(uri).build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody User user){
      userServices.update(id,user);
      return ResponseEntity.noContent().build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      userServices.delete(id);
      return ResponseEntity.noContent().build();
   }
   
}
