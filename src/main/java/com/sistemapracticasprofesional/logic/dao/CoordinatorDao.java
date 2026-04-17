package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
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

    public boolean insertCoordinator(CoordinatorDto coordinator) {
        
        String registerQuery = "INSERT INTO coordinador "
            + "(Numero_de_personal, Id_usuario, Nombre, Estado,"
            + "Fecha_de_registro, Fecha_de_termino) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery)) {

            preparedStatement.setInt(1, coordinator.getPersonnelNumber());
            if (coordinator.getUserId() != null) {
                preparedStatement.setInt(2, coordinator.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(3, coordinator.getName());
            preparedStatement.setString(4, coordinator.getState());
            preparedStatement.setDate(5, coordinator.getEntryDate() != null
                    ? Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(6, coordinator.getExitDate() != null
                    ? Date.valueOf(coordinator.getExitDate()) : null);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            
            LOGGER.error("Error inserting coordinator with user id {}", coordinator.getUserId(), e);
            throw new DaoException("Error registering coordinator", e);
        }
    }

    public boolean updateCoordinator(CoordinatorDto coordinator) {
        
        String updateQuery = "UPDATE coordinador SET Nombre = ?, Estado = ?, "
                + "Fecha_de_registro = ?, Fecha_de_termino = ? WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, coordinator.getName());
            preparedStatement.setString(2, coordinator.getState());
            preparedStatement.setDate(3, coordinator.getEntryDate() != null
                    ? Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(4, coordinator.getExitDate() != null
                    ? Date.valueOf(coordinator.getExitDate()) : null);
            if (coordinator.getUserId() != null) {
                preparedStatement.setInt(5, coordinator.getUserId());
            } else {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            }

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            
            LOGGER.error("Error updating coordinator with user id {}", coordinator.getUserId(), e);
            throw new DaoException("Error updating coordinator", e);
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
            throw new DaoException("Error deleting coordinator", e);
        }
    }
    
    public CoordinatorDto getCoordinatorById(int id) {
        
        String getByIdQuery = "SELECT * FROM coordinador WHERE Id_usuario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getByIdQuery)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDTO(resultSet);
                }
            }
            return null;

        } catch (SQLException e) {
            
            LOGGER.error("Error getting coordinator by id {}", id, e);
            throw new DaoException("Error getting coordinator", e);
        }
    }
    
    public List<CoordinatorDto> getAllCoordinators() {
        
        List<CoordinatorDto> coordinatorList = new ArrayList<>();
        
        String getAllQuery = "SELECT * FROM coordinador";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                coordinatorList.add(mapResultSetToDTO(resultSet));
            }

            return coordinatorList;
        } catch (SQLException e) {
            
            LOGGER.error("Error getting coordinator list", e);
            throw new DaoException("Error getting coordinator list", e);
        }
    }

    private CoordinatorDto mapResultSetToDTO(ResultSet resultSet) throws SQLException {
        CoordinatorDto coordinatorObject = new CoordinatorDto();
        Integer userId = resultSet.getObject("Id_usuario") != null
                ? resultSet.getInt("Id_usuario")
                : null;
        coordinatorObject.setUserId(userId);
        coordinatorObject.setName(resultSet.getString("Nombre"));
        coordinatorObject.setState(resultSet.getString("Estado"));

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
