package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import com.sistemapracticasprofesional.logic.interfaces.IProfessor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessorDao implements IProfessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDao.class);

    private static final String INSERT_QUERY =
            "INSERT INTO profesor (Numero_de_personal, Id_usuario, Nombre, turno) "
            + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE_QUERY =
            "UPDATE profesor SET Id_usuario = ?, Nombre = ?, turno = ? "
            + "WHERE Numero_de_personal = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM profesor WHERE Numero_de_personal = ?";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM profesor WHERE Numero_de_personal = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM profesor";

    @Override
    public boolean insertProfessor(ProfessorDto professor) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, professor.getStaffNumber());

            if (professor.getUserId() != null) {
                preparedStatement.setInt(2, professor.getUserId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(3, professor.getName());
            preparedStatement.setString(4, professor.getShift());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error inserting professor with staff number {}", professor.getStaffNumber(), e);
            throw new DatabaseOperationException("Error al registrar el profesor", e);
        }
    }

    @Override
    public boolean updateProfessor(ProfessorDto professor) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            if (professor.getUserId() != null) {
                preparedStatement.setInt(1, professor.getUserId());
            } else {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            }

            preparedStatement.setString(2, professor.getName());
            preparedStatement.setString(3, professor.getShift());
            preparedStatement.setInt(4, professor.getStaffNumber());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error updating professor with staff number {}", professor.getStaffNumber(), e);
            throw new DatabaseOperationException("Error al actualizar el profesor", e);
        }
    }

    @Override
    public boolean deleteProfessor(int staffNumber) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, staffNumber);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.error("Error deleting professor with staff number {}", staffNumber, e);
            throw new DatabaseOperationException("Error al eliminar el profesor", e);
        }
    }

    @Override
    public ProfessorDto getProfessorByStaffNumber(int staffNumber) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

            preparedStatement.setInt(1, staffNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDto(resultSet);
                }
            }

            return null;

        } catch (SQLException e) {
            LOGGER.error("Error getting professor with staff number {}", staffNumber, e);
            throw new DatabaseOperationException("Error al obtener el profesor", e);
        }
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        List<ProfessorDto> professorList = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                professorList.add(mapResultSetToDto(resultSet));
            }

            return professorList;

        } catch (SQLException e) {
            LOGGER.error("Error getting professor list", e);
            throw new DatabaseOperationException("Error al obtener la lista de profesores", e);
        }
    }

    private ProfessorDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        Integer userId = resultSet.getObject("Id_usuario") != null
                ? resultSet.getInt("Id_usuario")
                : null;

        return new ProfessorDto(
                resultSet.getInt("Numero_de_personal"),
                userId,
                resultSet.getString("Nombre"),
                resultSet.getString("turno")
        );
    }
}