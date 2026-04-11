package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.OperationException;
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

        try (Connection connection = new DatabaseConnection().getConnection();
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
            throw new OperationException("Error al verificar el usuario", e);
        }
    }

    @Override
    public boolean registredUser(int idUser, String userName, String userPassword) {
        String query = "INSERT INTO usuario (Id_usuario, nombre, Contraseña) VALUES (?, ?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userPassword);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error registering user with id {}", idUser, e);
            throw new OperationException("Error al registrar el usuario", e);
        }
    }

    @Override
    public boolean updateName(String newName, int idUser) {
        String query = "UPDATE usuario SET nombre = ? WHERE Id_usuario = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, idUser);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating name for user id {}", idUser, e);
            throw new OperationException("Error al actualizar el nombre del usuario", e);
        }
    }

    @Override
    public boolean updatePassword(String newPassword, int idUser) {
        String query = "UPDATE usuario SET Contraseña = ? WHERE Id_usuario = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, idUser);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating password for user id {}", idUser, e);
            throw new OperationException("Error al actualizar la contraseña del usuario", e);
        }
    }

    @Override
    public UserDto getUser(int idUser) {
        String query = "SELECT * FROM usuario WHERE Id_usuario = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
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
            throw new OperationException("Error al obtener el usuario", e);
        }
    }
}