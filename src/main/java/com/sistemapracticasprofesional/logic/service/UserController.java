package com.sistemapracticasprofesional.logic.controller;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;

public class UserController {
    private UserDao userDao = new UserDao();

    public void logginUser(UserDto userDto) throws BusinessLogicException{

        if ( userDto == null ) {
            throw new BusinessLogicException("Los campos estan vacios");
        }
        
    }
}
