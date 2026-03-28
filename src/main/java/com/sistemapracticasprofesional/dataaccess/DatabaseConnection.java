package com.sistemapracticasprofesional.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/swpp";
    private static final String USER = "adminBDDS2";
    private static final String PASSWORD = "Gl01nkSy123";
    
    private static Connection connection = null;
    
    private DatabaseConnection(){}
    
    public static Connection getConnection(){
        
        try{
            if(connection == null || connection.isClosed()){
            
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            
            }
        }catch (SQLException e) {
                System.err.println("Error de SQL al intentar conectar: " + e.getMessage());
    }
        return connection;
    }
    
    public static void endConnection(Connection connection, PreparedStatement
            prepStatement, ResultSet resultSet)
    { /*
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {}
        
        try {
            if (prepStatement != null) prepStatement.close();
        } catch (SQLException e) {}
        
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {} 
        */ // redo this shit
    }
    
}