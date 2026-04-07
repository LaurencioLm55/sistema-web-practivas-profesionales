package edu.uv.sistemapracticasprofesionales.logic.dao;
import edu.uv.sistemapracticasprofesionales.logic.interfaces.IUserDao;
import edu.uv.sistemapracticasprofesionales.logic.dto.UserDto;
import edu.uv.sistemapracticasprofesionales.dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;


public class UserDao implements IUserDao{
    
    @Override
    public boolean isUserRegistred(UserDto user) throws SQLException{
        
        boolean success = false;
        int result = 0;
        DatabaseConnection databaseConnection = new DatabaseConnection();   
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String query = "SELECT EXISTS ( SELECT 1 FROM usuario WHERE nombre = ? AND contraseña = ?) AS existe;";
        
        try{
            
            connection = databaseConnection.getConnection();
            
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                result = resultSet.getInt("Existe");
            }
            
            if(result == 1){
                success = true;
            }
           
            
        }finally{
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        }
        return success;
        
    }
    
    @Override
    public boolean registredUser(int idUser, String userName, String userPassword) throws SQLException {
        
        boolean success = false;
        Connection connection = null;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO usuario (id_usuario,nombre,contraseña) VALUES (?,?,?);";
        
        try{
 
           connection = databaseConnection.getConnection();
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1, idUser);
           preparedStatement.setString(2, userName);
           preparedStatement.setString(3, userPassword);
           
           int affectedRows = preparedStatement.executeUpdate();
           
           if(affectedRows > 0){
               success = true;
           }
           
        }finally{
            preparedStatement.close();
            connection.close();
        }
        
        return success;
        
    }
    
    @Override
    public boolean updateName(String newName, int idUser)throws SQLException{
        
        boolean success = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String query = "UPDATE usuario SET nombre = ? WHERE Id_usuario = ?;";
        
        try{
            
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, 1);
            int affectedRows = preparedStatement.executeUpdate();
            
            if(affectedRows > 0){
                success = true;
            }
            
        }finally{
            
            preparedStatement.close();
            connection.close();
            
        }
        
        return success;
        
    }
    
    @Override
    public boolean updatePassword( String newPassword, int idUser) throws SQLException {
       
        boolean success = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String query = "UPDATE usuario SET contraseña = ? WHERE id_usuario = ?;";
        
        try{
            
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, idUser);
            
            int affectedRows = preparedStatement.executeUpdate();
            
            if ( affectedRows > 0 ){
                
                success = true;
                
            }
        }finally{
            
            preparedStatement.close();
            connection.close();
            
        }
        
        return success;
    }
    
    @Override
        public UserDto getUser(int idUser) throws SQLException {

            UserDto user = new UserDto();
            DatabaseConnection databaseConnection = new DatabaseConnection();
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM usuario WHERE id_usuario = ?;";

            try{

                connection = databaseConnection.getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idUser);

                resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                    
                    user.setUserName(resultSet.getString("nombre"));
                    user.setPassword(resultSet.getString("contraseña"));
                    
                }


            }finally{
                
                resultSet.close();
                preparedStatement.close();
                connection.close();

            }

            return user;

        }    
  
    
}
