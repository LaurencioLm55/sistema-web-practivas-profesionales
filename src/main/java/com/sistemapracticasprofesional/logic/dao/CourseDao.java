package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.interfaces.ICourse;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.LoggerFactory;


public class CourseDao implements ICourse {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CourseDao.class);
    
    @Override
    public boolean registerCourse(CourseDto course) {
        
        boolean success = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String query = "INSERT INTO experiencia_educativa (NRC, Numero_de_personal"
                 + ", Estado, Periodo, Seccion, ArchivoFormato) VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, course.getNrc());
            preparedStatement.setInt(2, course.getStaffNumber());
            preparedStatement.setString(3, course.getStatus());
            preparedStatement.setString(4, course.getPeriod());
            preparedStatement.setString(5, course.getSection());
            preparedStatement.setString(6, course.getFormatFile());
            
        }catch(SQLException e){
        }finally{
            if (preparedStatement != null ){
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    logger.log(Logger.Level.ERROR, (String) null, ex);
                }
            }
            if (connection != null ){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.log(Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
        return success;
    }

        @Override
        public boolean updateCourse(String data, String newData, int nrc) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public CourseDto getCourse(int nrc) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<CourseDto> getListCourse(String filtrer) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        
}

