package com.sistemapracticasprofesional.logic;

import com.sistemapracticasprofesional.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InternDAO {

    public boolean insertar(InternDTO practicante) {
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
