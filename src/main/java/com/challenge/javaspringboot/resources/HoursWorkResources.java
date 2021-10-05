package com.challenge.javaspringboot.resources;

import java.net.URI;
import java.util.List;

import com.challenge.javaspringboot.domain.HoursWork;
import com.challenge.javaspringboot.services.HoursWorkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "hours")
public class HoursWorkResources {
   @Autowired
   private HoursWorkService service;

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<HoursWork>> findAll(){
      List<HoursWork> hours = service.findAll();
      return ResponseEntity.ok().body(hours);
   }
   @RequestMapping( value ="/{id}", method = RequestMethod.GET)
   public ResponseEntity<HoursWork> findById(@PathVariable Integer id){
      HoursWork hours = service.findById(id);
      return ResponseEntity.ok().body(hours);
   }

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody HoursWork hours){
      Integer id = service.insert(hours);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
         .buildAndExpand(id).toUri();
      return ResponseEntity.created(uri).build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody HoursWork hours){
      service.update(id, hours);
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
   @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
   public ResponseEntity<List<HoursWork>> findHoursByUser(@PathVariable Integer id){
      List<HoursWork> hours = service.findHoursByUser(id);
      return ResponseEntity.ok().body(hours);   
   }

   
}
