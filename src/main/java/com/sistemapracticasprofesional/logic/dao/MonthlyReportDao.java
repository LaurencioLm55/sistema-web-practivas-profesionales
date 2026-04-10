package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import com.sistemapracticasprofesional.logic.interfaces.IMonthlyReportDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonthlyReportDao implements IMonthlyReportDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonthlyReportDao.class);

    @Override
    public boolean registredReport(MonthlyReportDto monthlyReport) {
        String query = "INSERT INTO reporteavances "
                + "(IdActividadProyecto, Matricula, Calificacion, ArchivoReporte, "
                + "Fecha_de_realizacion, Fecha_de_entrega, DescripcionReporte) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, monthlyReport.getMonthlyReportId());
            preparedStatement.setString(2, monthlyReport.getInternId());
            preparedStatement.setFloat(3, monthlyReport.getScore());
            preparedStatement.setString(4, monthlyReport.getMonthlyReportFile());
            preparedStatement.setDate(5, monthlyReport.getDateOfCompletion() != null
                    ? Date.valueOf(monthlyReport.getDateOfCompletion()) : null);
            preparedStatement.setDate(6, monthlyReport.getDeliveryDate() != null
                    ? Date.valueOf(monthlyReport.getDeliveryDate()) : null);
            preparedStatement.setString(7, monthlyReport.getDescription());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error registering monthly report for intern {}", monthlyReport.getInternId(), e);
            throw new DatabaseOperationException("Error al registrar el reporte mensual", e);
        }
    }

    @Override
    public boolean updateMonthlyReport(String data, String newData, int idMonthlyReport) {
        Set<String> allowedFields = Set.of(
                "Matricula",
                "Calificacion",
                "ArchivoReporte",
                "Fecha_de_realizacion",
                "Fecha_de_entrega",
                "DescripcionReporte"
        );

        if (data == null || !allowedFields.contains(data)) {
            throw new IllegalArgumentException("Campo no permitido: " + data);
        }

        String query = "UPDATE reporteavances SET " + data + " = ? WHERE IdActividadProyecto = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newData);
            preparedStatement.setInt(2, idMonthlyReport);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error updating monthly report id {}", idMonthlyReport, e);
            throw new DatabaseOperationException("Error al actualizar el reporte mensual", e);
        }
    }

    @Override
    public MonthlyReportDto getMonthlyReport(int idMonthlyReport) {
        String query = "SELECT * FROM reporteavances WHERE IdActividadProyecto = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idMonthlyReport);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    MonthlyReportDto monthlyReport = new MonthlyReportDto();
                    monthlyReport.setMonthlyReportId(resultSet.getInt("IdActividadProyecto"));
                    monthlyReport.setInternId(resultSet.getString("Matricula"));
                    monthlyReport.setScore(resultSet.getFloat("Calificacion"));
                    monthlyReport.setMonthlyReportFile(resultSet.getString("ArchivoReporte"));

                    Date completionDate = resultSet.getDate("Fecha_de_realizacion");
                    if (completionDate != null) {
                        monthlyReport.setDateOfCompletion(completionDate.toLocalDate());
                    }

                    Date deliveryDate = resultSet.getDate("Fecha_de_entrega");
                    if (deliveryDate != null) {
                        monthlyReport.setDeliveryDate(deliveryDate.toLocalDate());
                    }

                    monthlyReport.setDescription(resultSet.getString("DescripcionReporte"));
                    return monthlyReport;
                }
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Error getting monthly report id {}", idMonthlyReport, e);
            throw new DatabaseOperationException("Error al obtener el reporte mensual", e);
        }
    }

    @Override
    public List<MonthlyReportDto> getListMonthlyReport(String idIntern) {
        List<MonthlyReportDto> monthlyReportList = new ArrayList<>();
        String query = "SELECT * FROM reporteavances WHERE Matricula = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, idIntern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MonthlyReportDto monthlyReport = new MonthlyReportDto();
                    monthlyReport.setMonthlyReportId(resultSet.getInt("IdActividadProyecto"));
                    monthlyReport.setInternId(resultSet.getString("Matricula"));
                    monthlyReport.setScore(resultSet.getFloat("Calificacion"));
                    monthlyReport.setMonthlyReportFile(resultSet.getString("ArchivoReporte"));

                    Date completionDate = resultSet.getDate("Fecha_de_realizacion");
                    if (completionDate != null) {
                        monthlyReport.setDateOfCompletion(completionDate.toLocalDate());
                    }

                    Date deliveryDate = resultSet.getDate("Fecha_de_entrega");
                    if (deliveryDate != null) {
                        monthlyReport.setDeliveryDate(deliveryDate.toLocalDate());
                    }

                    monthlyReport.setDescription(resultSet.getString("DescripcionReporte"));
                    monthlyReportList.add(monthlyReport);
                }
            }

            return monthlyReportList;
        } catch (SQLException e) {
            LOGGER.error("Error getting monthly reports for intern {}", idIntern, e);
            throw new DatabaseOperationException("Error al obtener la lista de reportes mensuales", e);
        }
    }
}