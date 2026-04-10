package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinatorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinatorDao.class);

    public CoordinatorDto getCoordinatorById(int id) {
        String query = "SELECT * FROM coordinador WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return convertResultSetToDTO(resultSet);
                }
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Error getting coordinator by id {}", id, e);
            throw new DatabaseOperationException("Error al obtener el coordinador", e);
        }
    }

    public List<CoordinatorDto> getAllCoordinators() {
        List<CoordinatorDto> coordinatorList = new ArrayList<>();
        String query = "SELECT * FROM coordinador";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                coordinatorList.add(convertResultSetToDTO(resultSet));
            }

            return coordinatorList;
        } catch (SQLException e) {
            LOGGER.error("Error getting coordinator list", e);
            throw new DatabaseOperationException("Error al obtener la lista de coordinadores", e);
        }
    }

    public boolean insertCoordinator(CoordinatorDto coordinator) {
        String query = "INSERT INTO coordinador "
                + "(Id_usuario, Nombre, Estado, Fecha_de_registro, Fecha_de_termino) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, coordinator.getUserId());
            preparedStatement.setString(2, coordinator.getName());
            preparedStatement.setString(3, coordinator.getState());
            preparedStatement.setDate(4, coordinator.getEntryDate() != null
                    ? Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(5, coordinator.getExitDate() != null
                    ? Date.valueOf(coordinator.getExitDate()) : null);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error inserting coordinator with user id {}", coordinator.getUserId(), e);
            throw new DatabaseOperationException("Error al registrar el coordinador", e);
        }
    }

    public boolean updateCoordinator(CoordinatorDto coordinator) {
        String query = "UPDATE coordinador SET Nombre = ?, Estado = ?, "
                + "Fecha_de_registro = ?, Fecha_de_termino = ? WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, coordinator.getName());
            preparedStatement.setString(2, coordinator.getState());
            preparedStatement.setDate(3, coordinator.getEntryDate() != null
                    ? Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(4, coordinator.getExitDate() != null
                    ? Date.valueOf(coordinator.getExitDate()) : null);
            preparedStatement.setInt(5, coordinator.getUserId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating coordinator with user id {}", coordinator.getUserId(), e);
            throw new DatabaseOperationException("Error al actualizar el coordinador", e);
        }
    }

    public boolean deleteCoord(int userId) {
        String query = "DELETE FROM coordinador WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error deleting coordinator with user id {}", userId, e);
            throw new DatabaseOperationException("Error al eliminar el coordinador", e);
        }
    }

    private CoordinatorDto convertResultSetToDTO(ResultSet resultSet) throws SQLException {
        CoordinatorDto coordinatorObject = new CoordinatorDto();
        coordinatorObject.setUserId(resultSet.getInt("Id_usuario"));
        coordinatorObject.setName(resultSet.getString("Nombre"));
        coordinatorObject.setState(resultSet.getBoolean("Estado"));

        Date entryDate = resultSet.getDate("Fecha_de_registro");
        if (entryDate != null) {
            coordinatorObject.setEntryDate(entryDate.toLocalDate());
        }

        Date exitDate = resultSet.getDate("Fecha_de_termino");
        if (exitDate != null) {
            coordinatorObject.setExitDate(exitDate.toLocalDate());
        }

        return coordinatorObject;
    }
}