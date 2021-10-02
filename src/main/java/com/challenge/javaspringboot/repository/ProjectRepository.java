package com.challenge.javaspringboot.repository;

import com.challenge.javaspringboot.domain.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
   
}
