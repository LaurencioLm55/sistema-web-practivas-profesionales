package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserValidator {
    private UserDto userDto; 

    public UserValidator (UserDto userDto) {
        this.userDto = userDto;

    }

    public boolean isUserNameValid(){
        return userDto != null
                && userDto.getUserName() != null
                && !userDto.getUserName().isBlank();
    }

    public boolean isUserPasswordValid() {
        return userDto != null
                && userDto.getPassword() != null
                && !userDto.getPassword().isBlank();
    }
}
