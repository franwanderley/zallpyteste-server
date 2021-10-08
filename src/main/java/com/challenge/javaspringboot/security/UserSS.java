package com.challenge.javaspringboot.security;

import java.util.Collection;

import com.challenge.javaspringboot.domain.enums.TypeCharge;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails{
   private static final long serialVersionUID = 1L;

   private Integer id;
   private String email;
   private String password;
   private Collection<? extends GrantedAuthority> authorities;
   
   public UserSS(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.authorities = authorities;
   }

   public UserSS() {
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities(){
      return authorities;
   }
   public Integer getId(){
      return id;
   }

  @Override
  public  String getPassword(){
      return password;
  }
  @Override
  public String getUsername(){
   return email;
  }
  @Override
  public boolean isAccountNonExpired(){
      return true;
  }
  @Override
  public boolean isAccountNonLocked(){
      return true;
  }
  @Override
  public boolean isCredentialsNonExpired(){
     return true;
  }
  @Override
  public boolean isEnabled(){
      return true;
  }

   public boolean hasRole(TypeCharge typeCharge) {
      return getAuthorities().contains(new SimpleGrantedAuthority(typeCharge.getDescricao()));
   }
}
