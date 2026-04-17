package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.interfaces.ICourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseDao implements ICourse {

   private static final Logger LOGGER = LoggerFactory.getLogger(CourseDao.class);

    @Override
    public boolean registerCourse(CourseDto course) {
        String query = "INSERT INTO experiencia_educativa "
                + "(NRC, Numero_de_personal, Estado, Periodo, Seccion, ArchivoFormato) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, course.getNrc());
            preparedStatement.setInt(2, course.getStaffNumber());
            preparedStatement.setString(3, course.getStatus());
            preparedStatement.setString(4, course.getPeriod());
            preparedStatement.setString(5, course.getSection());
            preparedStatement.setString(6, course.getFormatFile());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error registering course", e);
            throw new DaoException("Error registering course", e);
        }
    }

    @Override
    public boolean updateCourse(String field, String newData, int nrc) {
        List<String> allowedFields = List.of(
                "Estado",
                "Periodo",
                "Seccion",
                "ArchivoFormato"
        );

        if (!allowedFields.contains(field)) {
            throw new IllegalArgumentException("Field not allowed: " + field);
        }

        String query = "UPDATE experiencia_educativa SET " + field + " = ? WHERE NRC = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newData);
            preparedStatement.setInt(2, nrc);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error updating course", e);
            throw new DaoException("Error updating course with NRC: "
                    + nrc, e);
        }
    }

    @Override
    public CourseDto getCourse(int nrc) {
        String query = "SELECT * FROM experiencia_educativa WHERE NRC = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, nrc);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new CourseDto(
                            resultSet.getInt("NRC"),
                            resultSet.getInt("Numero_de_personal"),
                            resultSet.getString("Estado"),
                            resultSet.getString("Periodo"),
                            resultSet.getString("Seccion"),
                            resultSet.getString("ArchivoFormato")
                    );
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting course with NRC {}", nrc, e);
            throw new DaoException("Error getting course with NRC: "
                    + nrc, e);
        }
        return null;
    }

    @Override
    public List<CourseDto> getListCourse(String filter) {
        List<CourseDto> courseList = new ArrayList<>();
        String query = "SELECT * FROM experiencia_educativa WHERE Estado LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + filter + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseDto course = new CourseDto(
                            resultSet.getInt("NRC"),
                            resultSet.getInt("Numero_de_personal"),
                            resultSet.getString("Estado"),
                            resultSet.getString("Periodo"),
                            resultSet.getString("Seccion"),
                            resultSet.getString("ArchivoFormato")
                    );

                    courseList.add(course);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting course list with filter {}", filter, e);
            throw new DaoException("Error getting course list", e);
        }

        return courseList;
    }
}
