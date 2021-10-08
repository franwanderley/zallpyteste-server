package com.challenge.javaspringboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/")
public class HelloWordControllers {
   
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<String> helloWord(){
      return ResponseEntity.ok().body("Hello Word!");
   }
}
