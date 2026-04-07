/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.logic.dto.CoordinatorDTO;
import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dario Padilla
 */
public class CoordinatorDAO
{
    public CoordinatorDTO getCoordinator(int id) throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CoordinatorDTO coordinator = null;
        String query = "SELECT * FROM coordinador WHERE Id_usuario = ?";
        
        try
        {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                coordinator = convertResultSetToDTO(resultSet);
            }
        } finally 
        {
            DatabaseConnection.closeConnection();
        }
        
        return coordinator;
    }
    
    public List<CoordinatorDTO> getAllCoordinators() throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CoordinatorDTO> coordinatorList = new ArrayList<>();
        String query = "SELECT * FROM coordinador";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                coordinatorList.add(convertResultSetToDTO(resultSet));
            }
        } finally {
            DatabaseConnection.closeConnection();
        }
        return coordinatorList;
    }
    
    void insertCoordinator(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO coordinador (Id_usuario, Nombre, Estado, "
                + "Fecha_de_registro, Fecha_de_termino) VALUES (?, ?, ?, ?, ?)"; 
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, coordinator.getUserId());
            preparedStatement.setString(2, coordinator.getName());
            preparedStatement.setString(3, coordinator.getState());
            preparedStatement.setDate(4, coordinator.getEntryDate() != null ? 
                    Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(5, coordinator.getExitDate() != null ? 
                    Date.valueOf(coordinator.getExitDate()) : null);

            preparedStatement.executeUpdate();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
    
    void updateCoordinator(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE coordinador SET Nombre = ?, Estado = ?,"
                + " Fecha_de_registro = ?, Fecha_de_termino = ? WHERE Id_usuario = ?";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, coordinator.getName());
            preparedStatement.setString(2, coordinator.getState());
            preparedStatement.setDate(3, coordinator.getEntryDate() != null ? 
                    Date.valueOf(coordinator.getEntryDate()) : null);
            preparedStatement.setDate(4, coordinator.getExitDate() != null ? 
                    Date.valueOf(coordinator.getExitDate()) : null);
            preparedStatement.setInt(5, coordinator.getUserId());

            preparedStatement.executeUpdate();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
    
    void deleteCoord(CoordinatorDTO coordinator) throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM coordinador WHERE Id_usuario = ?";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, coordinator.getUserId());
            preparedStatement.executeUpdate();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
    
    private CoordinatorDTO convertResultSetToDTO(ResultSet resultSet) throws SQLException 
    {
        CoordinatorDTO coordinatorObject = new CoordinatorDTO();
        coordinatorObject.setUserId(resultSet.getInt("Id_usuario"));
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