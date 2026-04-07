package com.sistemapracticasprofesional.logic.dto;

public class UserDto {
    private String userName;
    private String password;
    
    public UserDto(){}
    
    public UserDto(String userName, String password){
        this.userName = userName;
        this.password = password;
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
