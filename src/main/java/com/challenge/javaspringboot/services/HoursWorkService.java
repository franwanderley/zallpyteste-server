package com.challenge.javaspringboot.services;

import java.util.List;
import java.util.Optional;

import com.challenge.javaspringboot.domain.HoursWork;
import com.challenge.javaspringboot.repository.HoursWorkRepository;

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
      return obj.orElseThrow();
   }

   public void delete(Integer id){
      HoursWork hours = findById(id);
      repo.delete(hours);
   }
}
