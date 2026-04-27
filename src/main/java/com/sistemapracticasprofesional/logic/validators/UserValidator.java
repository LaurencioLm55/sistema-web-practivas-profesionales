package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserValidator {
    private UserDto userDto; 
    private String text;

    public UserValidator (UserDto userDto) {
        this.userDto = userDto;

    }

    public boolean isUserNameValid(){

        if (userDto.getUserName().isBlank()){
            
        }

    }

    public boolean isUserPasswordValid() {

    }



}