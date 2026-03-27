package com.sistemapracticasprofesional.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "";
    private static final String user = "";
    private static final String password = "";
    
    private static Connection connection = null;
    
    private DatabaseConnection(){}
    
    public static Connection getConnection(){
        
        try{
            if(connection == null || connection.isClosed()){
            
                connection = DriverManager.getConnection(URL,user,password);
            
            }
        }catch (SQLException e) {
                System.err.println("Error de SQL al intentar conectar: " + e.getMessage());
        
        
    }
        return connection;
    }
}