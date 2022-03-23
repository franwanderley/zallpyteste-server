package com.challenge.javaspringboot.dto;

import java.io.Serializable;

public class NewPasswordDTO implements Serializable {
   private static final long serialVersionUID = 1L;

   private String password;

   public NewPasswordDTO() {
   }

   public NewPasswordDTO(String password) {
      this.password = password;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
