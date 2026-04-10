package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sistemapracticasprofesional.logic.interfaces.IIntern;

public class InternDao implements IIntern {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternDao.class);

    private static final String INSERT_QUERY =
            "INSERT INTO practicante (Matricula, Nombre, Edad, Genero, Carrera, LenguaIndigena) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY =
            "UPDATE practicante SET Nombre = ?, Edad = ?, Genero = ?, Carrera = ?, LenguaIndigena = ? "
            + "WHERE Matricula = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM practicante WHERE Matricula = ?";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM practicante WHERE Matricula = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM practicante";

    @Override
    public boolean insertIntern(InternDto intern) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, intern.getMatricula());
            preparedStatement.setString(2, intern.getNombre());
            preparedStatement.setInt(3, intern.getEdad());
            preparedStatement.setString(4, intern.getGenero());
            preparedStatement.setString(5, intern.getCarrera());
            preparedStatement.setString(6, intern.getLenguaIndigena());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error inserting intern with matricula {}", intern.getMatricula(), e);
            throw new DatabaseOperationException("Error al registrar el practicante", e);
        }
    }

    @Override
    public boolean updateIntern(InternDto intern) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, intern.getNombre());
            preparedStatement.setInt(2, intern.getEdad());
            preparedStatement.setString(3, intern.getGenero());
            preparedStatement.setString(4, intern.getCarrera());
            preparedStatement.setString(5, intern.getLenguaIndigena());
            preparedStatement.setString(6, intern.getMatricula());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error updating intern with matricula {}", intern.getMatricula(), e);
            throw new DatabaseOperationException("Error al actualizar el practicante", e);
        }
    }

    @Override
    public boolean deleteIntern(String matricula) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setString(1, matricula);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting intern with matricula {}", matricula, e);
            throw new DatabaseOperationException("Error al eliminar el practicante", e);
        }
    }

    @Override
    public InternDto getInternByMatricula(String matricula) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

            preparedStatement.setString(1, matricula);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

            return null;

        } catch (SQLException e) {
            LOGGER.error("Error getting intern with matricula {}", matricula, e);
            throw new DatabaseOperationException("Error al obtener el practicante", e);
        }
    }

    @Override
    public List<InternDto> getAllInterns() {
        List<InternDto> internList = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                internList.add(mapResultSetToDto(resultSet));
            }

            return internList;

        } catch (SQLException e) {
            LOGGER.error("Error getting intern list", e);
            throw new DatabaseOperationException("Error al obtener la lista de practicantes", e);
        }
    }

    private InternDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        return new InternDto(
                resultSet.getString("Matricula"),
                resultSet.getInt("Edad"),
                resultSet.getString("Nombre"),
                resultSet.getString("LenguaIndigena"),
                resultSet.getString("Genero"),
                resultSet.getString("Carrera")
        );
    }
}