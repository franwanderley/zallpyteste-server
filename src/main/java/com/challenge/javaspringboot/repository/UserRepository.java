package com.challenge.javaspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.challenge.javaspringboot.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   
   public Optional<User> findByEmail(String email);
}