package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.PartialReportDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.IPartialReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartialReportDao implements IPartialReport{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PartialReportDao.class);
    
    @Override
    public boolean insertPartialReport(PartialReportDto partialReport){
        
        String registerQuery = "INSERT into reporteparciales(IdActividadProyecto,"
                + "Tiempo_planeado, Tiempo_real, Resultados, Observaciones)"
                + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery)) {
            
            preparedStatement.setInt(1, partialReport.getProyectActivityId());
            preparedStatement.setString(2, partialReport.getPlannedTime());
            preparedStatement.setString(3, partialReport.getRealTime());
            preparedStatement.setString(4, partialReport.getResults());
            preparedStatement.setString(5, partialReport.getObservations());
            return preparedStatement.executeUpdate() > 0;
            
        } catch(SQLException e){
            
            LOGGER.error("Error registering monthly report for intern {}",
                    partialReport.getProyectActivityId(), e);
            throw new DaoException("Error registering monthly report", e);
        }
    }
    
    @Override
    public boolean updatePartialReport(PartialReportDto partialReport) {
        
        String updateQuery = "UPDATE reporteparciales SET Tiempo_planeado = ?,"
                + " Tiempo_real = ?, Resultados = ?, Observaciones = ? "
                + "WHERE IdActividadProyecto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, partialReport.getPlannedTime());
            preparedStatement.setString(2, partialReport.getRealTime());
            preparedStatement.setString(3, partialReport.getResults());
            preparedStatement.setString(4, partialReport.getObservations());
            preparedStatement.setInt(5, partialReport.getProyectActivityId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
        
            LOGGER.error("Error updating partial report with id {}",
                    partialReport.getProyectActivityId(), e);
            throw new DaoException("Error updating partial report", e);
        }
    }
    
    @Override
    public boolean deletePartialReport(int idPartialReport) {
        
        String deleteQuery = "DELETE FROM reporteparciales WHERE IdReporteParcial = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, idPartialReport);
            
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting partial report with id {}", idPartialReport, e);
            throw new DaoException("Error deleting partial report", e);
        }
    }
    
    @Override
    public PartialReportDto getPartialReportById(int idPartialReport) {
        String selectQuery = "SELECT IdReporteParcial, IdActividadProyecto, Tiempo_planeado, "
                + "Tiempo_real, Resultados, Observaciones "
                + "FROM reporteparciales WHERE IdReporteParcial = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, idPartialReport);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error retrieving partial report with id {}", idPartialReport, e);
            throw new DaoException("Error retrieving partial report", e);
        }
        return null;
    }

    @Override
    public List<PartialReportDto> getAllPartialReports() {
        String selectAllQuery = "SELECT IdReporteParcial, IdActividadProyecto, Tiempo_planeado, "
                + "Tiempo_real, Resultados, Observaciones "
                + "FROM reporteparciales";
        List<PartialReportDto> partialReports = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                partialReports.add(mapResultSetToDto(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error retrieving all partial reports", e);
            throw new DaoException("Error retrieving all partial reports", e);
        }
        return partialReports;
    }

    private PartialReportDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        return new PartialReportDto(
            resultSet.getInt("IdActividadProyecto"),
            resultSet.getString("Tiempo_planeado"),
            resultSet.getString("Tiempo_real"),
            resultSet.getString("Resultados"),
            resultSet.getString("Observaciones")
        );
    }
}
