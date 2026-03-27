/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemapracticasprofesional.logic;

import com.sistemapracticasprofesional.data.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dario Padilla
 */
public class CoordinatorDAO 
{
    CoordinatorDTO getCoordinator(Integer id) throws SQLException
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        CoordinatorDTO coordinator = null;
        String query = "SELECT * FROM coordinador WHERE Id_usuario = ?";
        
        try {
            connection = DatabaseConnection.getConnection();
            prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            if (resultSet.next()) {
                coordinator = convertResultSetToDTO(resultSet);
            }
        } finally {
            DatabaseConnection.endConnection(connection, prepStatement, resultSet);
        }
        return coordinator;
    }
    
    List<CoordinatorDTO> getAllCoordinators() throws SQLException
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        List<CoordinatorDTO> coordinatorList = new ArrayList<>();
        String query = "SELECT * FROM coordinador";

        try {
            connection = DatabaseConnection.getConnection();
            prepStatement = connection.prepareStatement(query);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                coordinatorList.add(convertResultSetToDTO(resultSet));
            }
        } finally {
            DatabaseConnection.endConnection(connection, prepStatement, resultSet);
        }
        return coordinatorList;
    }
    
    void insertCoordinator(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        String query = "INSERT INTO coordinador (Id_usuario, Nombre, Estado, "
                + "Fecha_de_registro, Fecha_de_termino) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DatabaseConnection.getConnection();
            prepStatement = connection.prepareStatement(query);

            prepStatement.setInt(1, coordinator.getUserId());
            prepStatement.setString(2, coordinator.getName());
            prepStatement.setString(3, coordinator.getState());
            prepStatement.setDate(4, coordinator.getEntryDate() != null ? 
                    Date.valueOf(coordinator.getEntryDate()) : null);
            prepStatement.setDate(5, coordinator.getExitDate() != null ? 
                    Date.valueOf(coordinator.getExitDate()) : null);

            prepStatement.executeUpdate();
        } finally {
            DatabaseConnection.endConnection(connection, prepStatement, null);
        }
    }
    
    void updateCoordinator(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        String query = "UPDATE coordinador SET Nombre = ?, Estado = ?,"
                + " Fecha_de_registro = ?, Fecha_de_termino = ? WHERE Id_usuario = ?";

        try {
            connection = DatabaseConnection.getConnection();
            prepStatement = connection.prepareStatement(query);

            prepStatement.setString(1, coordinator.getName());
            prepStatement.setString(2, coordinator.getState());
            prepStatement.setDate(3, coordinator.getEntryDate() != null ? 
                    Date.valueOf(coordinator.getEntryDate()) : null);
            prepStatement.setDate(4, coordinator.getExitDate() != null ? 
                    Date.valueOf(coordinator.getExitDate()) : null);
            prepStatement.setInt(5, coordinator.getUserId());

            prepStatement.executeUpdate();
        } finally {
            DatabaseConnection.endConnection(connection, prepStatement, null);
        }
    }
    
    void deleteCoord(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        String query = "DELETE FROM coordinador WHERE Id_usuario = ?";

        try {
            connection = DatabaseConnection.getConnection();
            prepStatement = connection.prepareStatement(query);

            prepStatement.setInt(1, coordinator.getUserId());
            prepStatement.executeUpdate();
        } finally {
            DatabaseConnection.endConnection(connection, prepStatement, null);
        }
    }
    
    private CoordinatorDTO convertResultSetToDTO(ResultSet resultSet) throws SQLException 
    {
        CoordinatorDTO dto = new CoordinatorDTO();
        dto.setUserId(resultSet.getInt("Id_usuario"));
        dto.setName(resultSet.getString("Nombre"));
        dto.setState(resultSet.getString("Estado"));

        Date entryDate = resultSet.getDate("Fecha_de_registro");
        if (entryDate != null) {
            dto.setEntryDate(entryDate.toLocalDate());
        }

        Date exitDate = resultSet.getDate("Fecha_de_termino");
        if (exitDate != null) {
            dto.setExitDate(exitDate.toLocalDate());
        }

        return dto;
    }
}