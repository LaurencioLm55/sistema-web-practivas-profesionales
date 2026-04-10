package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.logic.interfaces.IAffiliatedOrganization;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AffiliatedOrganizationDao implements IAffiliatedOrganization{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AffiliatedOrganizationDao.class);
    
    @Override
    public boolean registredOrganization(AffiliatedOrganizationDto affiliatedOrganization) {
        
        boolean success = false;
        
        String query = "INSERT INTO organizacion_vinculada (Id_organizacion,Nombre,Direccion,Sector,Ciudad"
                + "Estado,Telefono,Correo_electronico) VALUES (?,?,?,?,?,?,?,?);";
        
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
        
            preparedStatement.setInt(1, affiliatedOrganization.getIdOrganization());
            preparedStatement.setString(2, affiliatedOrganization.getName());
            preparedStatement.setString(3, affiliatedOrganization.getAddress());
            preparedStatement.setString(4, affiliatedOrganization.getSector());
            preparedStatement.setString(5, affiliatedOrganization.getCity());
            preparedStatement.setString(6, affiliatedOrganization.getState());
            preparedStatement.setString(7, affiliatedOrganization.getPhoneNumeber());
            preparedStatement.setString(8, affiliatedOrganization.getEmail());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            if(affectedRows > 0){
                
                success = true;
                
            }
            
            
        }catch (SQLException e) {           
            LOGGER.error("Error registred affiliatedOrganization: {}", affiliatedOrganization, e);
            throw new DatabaseOperationException("Error al verificar el usuario", e);
        }
        
        return success;
        
    }
    
    @Override
    public boolean updateAffiliatedOrganization(AffiliatedOrganizationDto affiliatedOrganization,int idAffiliatedOrganization){
        
        boolean success = false;
        String query = "UPDATE SET Nombre = ? Direccion = ? sector = ? Ciudad = ? Estado = ? Telefono = ? "
                + "Correo_electronico = ? WHERE Id_organizacion = ?;";
        
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            
            preparedStatement.setString(1, affiliatedOrganization.getName());
            preparedStatement.setString(2, affiliatedOrganization.getAddress());
            preparedStatement.setString(3, affiliatedOrganization.getSector());
            preparedStatement.setString(4, affiliatedOrganization.getCity());
            preparedStatement.setString(5, affiliatedOrganization.getState());
            preparedStatement.setString(6, affiliatedOrganization.getPhoneNumeber());
            preparedStatement.setString(7, affiliatedOrganization.getEmail());
            
            preparedStatement.setInt(8, idAffiliatedOrganization);
            
            int affectedRows = preparedStatement.executeUpdate();
            
            if(affectedRows > 0){
                
                success = true;
                
            }
            
            
        }catch (SQLException e) {           
            LOGGER.error("Error update affiliatedOrganization: {}", affiliatedOrganization, e);
            throw new DatabaseOperationException("Error al verificar el usuario", e);
        }
        
        return success;
        
    }
    
    @Override
    public AffiliatedOrganizationDto getAffiliatedOrganization(int idAffilitedOrganization){
        
        AffiliatedOrganizationDto affiliatedOrganization = new  AffiliatedOrganizationDto();
        String query = "SELECT * FROM organizacion_vinculada WHRE Id_organizacion =  ?;";
        
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                
                affiliatedOrganization.setName(resultSet.getString("Nombre"));
                affiliatedOrganization.setAddress(resultSet.getString("Direccion"));
                affiliatedOrganization.setSector(resultSet.getString("Sector"));
                affiliatedOrganization.setCity(resultSet.getString("Ciudad"));
                affiliatedOrganization.setState(resultSet.getString("Estado"));
                affiliatedOrganization.setPhoneNumber(resultSet.getString("Telefono"));
                affiliatedOrganization.setEmail(resultSet.getString("Correo_electronico"));
                
            }
            
            
        }catch (SQLException e) {           
            LOGGER.error("Error idAffiliated: {}", affiliatedOrganization, e);
            throw new DatabaseOperationException("Error al recuperar la organizacion", e);
        }
        
        return affiliatedOrganization;
        
        
    }
    
    @Override
    public List<AffiliatedOrganizationDto> getListAffiliatedOrganiztionActiveState(String activeState){
        
        List<AffiliatedOrganizationDto> affiliatedOrganizationList = new ArrayList<>();
        String query = "SELECT * FROM organizacion_vinculada WHERE estdo_de_actividad = ?;";
        
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){      
            
            preparedStatement.setString(1, activeState);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                
                AffiliatedOrganizationDto affiliatedOrganization = new AffiliatedOrganizationDto();
                
                affiliatedOrganization.setName(resultSet.getString("Nombre"));
                affiliatedOrganization.setAddress(resultSet.getString("Direccion"));
                affiliatedOrganization.setSector(resultSet.getString("Sector"));
                affiliatedOrganization.setCity(resultSet.getString("Ciudad"));
                affiliatedOrganization.setState(resultSet.getString("Estado"));
                affiliatedOrganization.setPhoneNumber(resultSet.getString("Telefono"));
                affiliatedOrganization.setEmail(resultSet.getString("Correo_electronico"));
                
                affiliatedOrganizationList.add(affiliatedOrganization);
           
            }
            
        }catch (SQLException e) {           
            LOGGER.error("Error : {}", activeState, e);
            throw new DatabaseOperationException("Error al verificar el usuario", e);
        }
       
        return affiliatedOrganizationList;
        
    }
}