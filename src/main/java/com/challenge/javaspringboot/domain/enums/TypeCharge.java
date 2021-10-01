package com.challenge.javaspringboot.domain.enums;

public enum TypeCharge {

   ADMINISTRADOR(1, "Adiministrador"),
   PROGRAMADOR(2, "Programador");

   private int cod;
   private String descricao; 

   private TypeCharge(int cod, String descricao){
      this.cod = cod;
      this.descricao = descricao;
   }
   private TypeCharge(){
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public int getCod() {
      return cod;
   }

   public void setCod(int cod) {
      this.cod = cod;
   }

   public static TypeCharge toEnum(Integer cod){
      if(cod == null)
         return null;
      for(TypeCharge x : TypeCharge.values()){
         if(cod.equals(x.getCod()))
            return x;
      }
      throw new IllegalArgumentException("Id invalido "+ cod);
   }
}
