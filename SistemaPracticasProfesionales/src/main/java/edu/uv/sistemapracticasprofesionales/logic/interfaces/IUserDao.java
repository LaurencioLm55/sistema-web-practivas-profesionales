package edu.uv.sistemapracticasprofesionales.logic.interfaces;
import edu.uv.sistemapracticasprofesionales.logic.dto.UserDto;
import java.sql.SQLException;


public interface IUserDao {
    boolean isUserRegistred(UserDto user) throws SQLException;
    boolean registredUser(int idUSer, String userName, String userPassword) throws SQLException;
    boolean updateName(String newName, int idUser)throws SQLException;
    boolean updatePassword(String newPassword, int idUser)throws SQLException;
    UserDto getUser(int idUser) throws SQLException;
}
