package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InternDao {

    public boolean insertar(InternDto practicante) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean registroExitoso = false;

        String sql = "INSERT INTO estudiantes (Matricula, Edad, Nombre, Primer_apellido, "
                   + "Segundo_apellido, LenguaIndigena, Genero, Carrera) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, practicante.getMatricula());
            preparedStatement.setInt(2, practicante.getEdad());
            preparedStatement.setString(3, practicante.getNombre());
            preparedStatement.setString(4, practicante.getApellidoPaterno());
            preparedStatement.setString(5, practicante.getApellidoMaterno());
            preparedStatement.setString(6, practicante.getLenguaIndigena());
            preparedStatement.setString(7, practicante.getGenero());
            preparedStatement.setString(8, practicante.getCarrera());
            
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                registroExitoso = true;
            }

        } catch (SQLException e) { 
            System.err.println("Error al intentar registrar practicante: " + e.getMessage());
        } finally {
            
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el Statement: " + e.getMessage());
            }

            
        }

        return registroExitoso;
    }
}
