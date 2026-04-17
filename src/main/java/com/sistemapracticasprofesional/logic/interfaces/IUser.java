package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;


public interface IUser {
    boolean isUserRegistred(UserDto user) throws DaoException;
    boolean insertUser(int idUSer, String userName, String userPassword) throws DaoException;
    boolean updateName(String newName, int idUser) throws DaoException;
    boolean updatePassword(String newPassword, int idUser) throws DaoException;
    UserDto getUser(int idUser) throws DaoException;
}
