package edu.uv.sistemapracticasprofesionales.logic.dao;
import edu.uv.sistemapracticasprofesionales.dataaccess.DatabaseConnection;
import edu.uv.sistemapracticasprofesionales.logic.dto.MonthlyReportDto;
import edu.uv.sistemapracticasprofesionales.logic.interfaces.IMonthlyReportDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Date;

public class MonthlyReportDao implements IMonthlyReportDao{
    
    @Override
    public boolean registredReport(MonthlyReportDto monthlyReport) throws SQLException{
        
        boolean success = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO reporteavances (IdActividadProyecto,Matricula,Calificacion,ArchivoReporte,"
                + "Fecha_de_realizacion,Fecha_de_entrega,DescripcionReporte) VALUES (?,?,?,?,?,?,?);";
        
        try{
            
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, monthlyReport.getMonthlyReportId());
            preparedStatement.setString(2, monthlyReport.getInternId());
            preparedStatement.setFloat(3, monthlyReport.getScore());
            preparedStatement.setString(4, monthlyReport.getMonthlyReportFile());
            preparedStatement.setDate(5, Date.valueOf(monthlyReport.getDateOfCompletion()));
            preparedStatement.setDate(6, Date.valueOf(monthlyReport.getDeliveryDate()));
            preparedStatement.setString (7, monthlyReport.getDescription());
            
            
            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0){
                
                success = true;
                
            }
            
            
        }finally{
            
            preparedStatement.close();
            connection.close();
            
        }
        
        return success;
        
    }
    
    @Override
    public boolean updateMonthlyReport(String data, String newData, int idMonthlyReport) throws SQLException{
        
        boolean success = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE reporteavances SET " + data + " = ? WHERE IdActividadProyecto = ?;";
        
        try{
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, newData);
            preparedStatement.setInt(2, idMonthlyReport);
            
            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0){
                
                success = true;
                
            }
            
        }finally{
            
        }
        
        return success;
        
    }
    
    @Override
    public MonthlyReportDto getMonthlyReport(int idMonthlyReport) throws SQLException{
        
        MonthlyReportDto monthlyReport = new MonthlyReportDto();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM reporteavances WHERE IdActividadProyecto = ?;";
        
        try{
            
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, idMonthlyReport);
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
               monthlyReport.setInternId(resultSet.getString("Matricula"));
               monthlyReport.setScore(resultSet.getFloat("Calificacion"));
               monthlyReport.setMonthlyReportFile(resultSet.getString("ArchivoReporte"));
               monthlyReport.setDateOfCompletion(resultSet.getDate("Fecha_de_realizacion").toLocalDate());
               monthlyReport.setDeliveryDate(resultSet.getDate("Fecha_de_entrega").toLocalDate());
               monthlyReport.setDescription(resultSet.getString("DescripcionReporte"));
            }
            
        }finally{
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        }
       
        return monthlyReport;
        
    }
    
    @Override
    public List<MonthlyReportDto> getListMonthlyReport(String idIntern)throws SQLException{
        
        List<MonthlyReportDto> monthlyReportList = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM reporteavances WHERE matricula = ?;";
        
        try{
            
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, idIntern);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
               
               MonthlyReportDto monthlyReport = new MonthlyReportDto();
               
               monthlyReport.setInternId(resultSet.getString("Matricula"));
               monthlyReport.setScore(resultSet.getFloat("Calificacion"));
               monthlyReport.setMonthlyReportFile(resultSet.getString("ArchivoReporte"));
               monthlyReport.setDateOfCompletion(resultSet.getDate("Fecha_de_realizacion").toLocalDate());
               monthlyReport.setDeliveryDate(resultSet.getDate("Fecha_de_entrega").toLocalDate());
               monthlyReport.setDescription(resultSet.getString("DescripcionReporte"));
               
               monthlyReportList.add(monthlyReport);
            }
            
        }finally{
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        }
       
        return monthlyReportList;
        
    }
}
