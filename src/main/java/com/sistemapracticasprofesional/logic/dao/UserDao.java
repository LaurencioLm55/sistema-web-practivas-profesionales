package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sistemapracticasprofesional.logic.interfaces.IUser;

public class UserDao implements IUser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    
    @Override
    public boolean isUserRegistred(UserDto user) {
        String query = "SELECT EXISTS ("
                + "SELECT 1 FROM usuario WHERE nombre = ? AND Contraseña = ?"
                + ") AS existe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("existe") == 1;
                }
            }

            return false;
        } catch (SQLException e) {
            LOGGER.error("Error checking if user is registered: {}", user.getUserName(), e);
            throw new DaoException("Error verifying user", e);
        }
    }

    @Override
    public boolean insertUser(UserDto userDto) {
        String query = "INSERT INTO usuario (Id_usuario, nombre, Contraseña) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userDto.gatIdUser());
            preparedStatement.setString(2, userDto.getUserName());
            preparedStatement.setString(3, userDto.getPassword());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            LOGGER.error("Error registering user with id {}", userDto.gatIdUser(), e);
            throw new DaoException("Error registering user", e);
        }

    }

    @Override
    public boolean updateUser( UserDto userDto ) {
        String query = "UPDATE usuario SET nombre = ? contraseña = ? WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userDto.getUserName());
            preparedStatement.setString(2, userDto.getPassword());

            preparedStatement.setInt(3, userDto.gatIdUser());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating name for user id {}", userDto.gatIdUser(), e);
            throw new DaoException("Error updating user name", e);
        }
    }

    @Override
    public UserDto getUser(int idUser) {
        String query = "SELECT * FROM usuario WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserDto user = new UserDto();
                    user.setUserName(resultSet.getString("nombre"));
                    user.setPassword(resultSet.getString("Contraseña"));
                    return user;
                }
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Error getting user with id {}", idUser, e);
            throw new DaoException("Error getting user", e);
        }
    }
}
