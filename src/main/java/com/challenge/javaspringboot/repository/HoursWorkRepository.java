package com.challenge.javaspringboot.repository;

import java.util.List;

import com.challenge.javaspringboot.domain.HoursWork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoursWorkRepository extends JpaRepository<HoursWork, Integer> {
   
   @Query("SELECT DISTINCT obj FROM HoursWork obj WHERE obj.user.id = :id")
   public List<HoursWork> findByUser(@Param("id") Integer id);
}