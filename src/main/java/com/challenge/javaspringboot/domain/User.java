package com.challenge.javaspringboot.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.challenge.javaspringboot.domain.enums.TypeCharge;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;
   private Integer charge;
   private String email;
   private String password;
   @ManyToMany(mappedBy = "users")
   @JsonIgnore
   private List<Project> projects; 
   
   public User(Integer id, String name, TypeCharge charge, String email, String password, List<Project> projects) {
      this.id = id;
      this.name = name;
      this.charge = charge.getCod();
      this.email = email;
      this.projects = projects;
      this.password = password;
   }
   public User() {
   }

   public List<Project> getProjects() {
      return projects;
   }
   public void setProject(List<Project> projects) {
      this.projects = projects;
   }
   
   public Integer getId() {
      return id;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public TypeCharge getCharge() {
      return TypeCharge.toEnum(charge);
   }
   public void setCharge(TypeCharge charge) {
      this.charge = charge.getCod();
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public int hashCode(){
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj){
      if(this == obj)
         return true;
      if(obj == null)
         return false;
      if(getClass() == obj.getClass())
         return false;
      User other = (User) obj;
      if(id == null){
         if(other.id == null)
         return false;
      }
      else if(!id.equals(other.id))
         return false;
      return true;
   }
}
