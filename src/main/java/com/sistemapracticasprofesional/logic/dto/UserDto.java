package com.sistemapracticasprofesional.logic.dto;

public class UserDto {
    private int idUser;
    private String userName;
    private String password;
    
    public UserDto(){}
    
    public UserDto(int idUser, String userName, String password){
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }

    public int gatIdUser(){
        return this.idUser;
    }

    public void setIdUser(int newIdUser){
        this.idUser = newIdUser;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public void setUserName(String newUserName){
        this.userName = newUserName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    
}
