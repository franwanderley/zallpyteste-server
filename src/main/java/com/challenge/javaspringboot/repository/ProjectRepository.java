package com.challenge.javaspringboot.repository;

import java.util.List;

import com.challenge.javaspringboot.domain.Project;
import com.challenge.javaspringboot.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
   public List<Project> findByUsers(User user);
}
