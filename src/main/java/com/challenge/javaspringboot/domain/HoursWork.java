package com.challenge.javaspringboot.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class HoursWork implements Serializable{
   private static final long serialVersionUID = 1L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @JsonFormat(pattern = "dd/MM/yyyy")
   private Date date;
   private Integer hours;
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   @ManyToOne
   @JoinColumn(name = "project_id")
   private Project project;

   public HoursWork(Integer id, Date date, Integer hours, User user, Project project) {
      this.id = id;
      this.date = date;
      this.hours = hours;
      this.user = user;
      this.project = project;
   }
   public HoursWork() {
   }

   public Integer getId() {
      return id;
   }
   public Project getProject() {
      return project;
   }
   public void setProject(Project project) {
      this.project = project;
   }
   public User getUser() {
      return user;
   }
   public void setUser(User user) {
      this.user = user;
   }
   public Integer getHours() {
      return hours;
   }
   public void setHours(Integer hours) {
      this.hours = hours;
   }
   public Date getDate() {
      return date;
   }
   public void setDate(Date date) {
      this.date = date;
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
      HoursWork other = (HoursWork) obj;
      if(id == null){
         if(other.id == null)
         return false;
      }
      else if(!id.equals(other.id))
         return false;
      return true;
   }
}
