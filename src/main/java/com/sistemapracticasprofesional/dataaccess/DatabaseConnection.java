package com.sistemapracticasprofesional.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String ENV_URL = "URL";
    private static final String ENV_USER = "USER";
    private static final String ENV_PASSWORD = "PASSWORD";
    
    private static Connection connection = null;
    
    private DatabaseConnection(){}
    
    public static Connection getConnection(){
        
        try {
            if (connection == null || connection.isClosed()) {
                String url = System.getenv(ENV_URL);
                String user = System.getenv(ENV_USER);
                String password = System.getenv(ENV_PASSWORD);

                if (url == null || user == null || password == null) {
                    throw new RuntimeException(
                        "Faltan variables de entorno. Asegúrate de definir: " +
                        ENV_URL + ", " + ENV_USER + ", " + ENV_PASSWORD
                    );
                }

                connection = DriverManager.getConnection(url, user, password);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage(), e);
        }
    }
        
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }
    
}
    
   