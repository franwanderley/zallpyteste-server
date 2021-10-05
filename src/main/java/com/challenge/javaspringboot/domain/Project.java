package com.challenge.javaspringboot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;
   private String description;
   @ManyToMany
   @JoinTable(
      name = "USER_PROJECT",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id")
   )
   private List<User> users = new ArrayList<User>();
   @JsonIgnore
   @OneToMany(mappedBy = "project")
   private List<HoursWork> hours = new ArrayList<HoursWork>();

   public Project(Integer id, String name, String description, List<User> users, List<HoursWork> hours) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.users = users;
      this.hours = hours;
   }
   public Project() {
   }
   
   public List<HoursWork> getHours() {
      return hours;
   }
   public void setHours(List<HoursWork> hours) {
      this.hours = hours;
   }
   public List<User> getUsers() {
      return users;
   }
   public void setUsers(List<User> users) {
      this.users = users;
   }

   public Integer getId() {
      return id;
   }
   public String getDescription() {
      return description;
   }
   public void setDescription(String description) {
      this.description = description;
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
      Project other = (Project) obj;
      if(id == null){
         if(other.id == null)
         return false;
      }
      else if(!id.equals(other.id))
         return false;
      return true;
   }
}
