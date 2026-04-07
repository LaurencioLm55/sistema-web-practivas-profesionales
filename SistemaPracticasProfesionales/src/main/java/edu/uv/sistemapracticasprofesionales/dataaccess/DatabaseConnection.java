package edu.uv.sistemapracticasprofesionales.dataaccess;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class DatabaseConnection {
    private String driver;
    private String user;
    private String password;
    private String url;
    private Connection connection;
    
    public DatabaseConnection(){
        
        Properties properties = new Properties();
        
        try(InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")){
            
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            
            this.driver = properties.getProperty("db.driver");
            this.user = properties.getProperty("db.user");
            this.password = properties.getProperty("db.password");
            this.url = properties.getProperty("db.url");
            
        }catch(IOException e){
            e.printStackTrace();
        }
            
    }
    
    public Connection getConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e){
            
        }catch(SQLException e){
            
        }
        return connection;
    }
}

