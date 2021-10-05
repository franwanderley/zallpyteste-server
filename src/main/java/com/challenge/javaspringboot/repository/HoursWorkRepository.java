package com.challenge.javaspringboot.repository;

import com.challenge.javaspringboot.domain.HoursWork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoursWorkRepository extends JpaRepository<HoursWork, Integer> {
   
}