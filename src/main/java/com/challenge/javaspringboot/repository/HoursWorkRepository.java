package com.challenge.javaspringboot.repository;

import java.util.List;

import com.challenge.javaspringboot.domain.HoursWork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoursWorkRepository extends JpaRepository<HoursWork, Integer> {
   
   @Query("SELECT DISTINCT obj FROM HoursWork obj WHERE obj.user.id = :idUser AND obj.project.id = :idProject ORDER BY obj.date")
   public List<HoursWork> findByUserAndProject(@Param("idProject") Integer idProject, @Param("idUser") Integer idUser);
   
   @Query("SELECT DISTINCT obj FROM HoursWork obj WHERE obj.project.id = :idProject ORDER BY obj.date")
   public List<HoursWork> findByProject(@Param("idProject") Integer idProject);
}