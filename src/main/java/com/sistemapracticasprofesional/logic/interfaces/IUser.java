package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import java.sql.SQLException;


public interface IUser {
    boolean isUserRegistred(UserDto user) throws SQLException;
    boolean registredUser(int idUSer, String userName, String userPassword) throws SQLException;
    boolean updateName(String newName, int idUser)throws SQLException;
    boolean updatePassword(String newPassword, int idUser)throws SQLException;
    UserDto getUser(int idUser) throws SQLException;
}
