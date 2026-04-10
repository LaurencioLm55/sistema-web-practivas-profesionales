package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DatabaseOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    
    private static final String INSERT_QUERY = "INSERT INTO proyecto (Id_organizacion, "
        + "projectName, projectDescription, projectMethodology, projectResources, "
        + "midtermProjectObjectives, generalProjectObjectives, inmediateProjectObjectives, "
        + "projectResponsabilities, projectAttendantName, projectAttendantEmail, "
        + "projectAttendantPosition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_QUERY = "UPDATE Project SET linkedOrganizationId = ?, "
        + "projectName = ?, projectDescription = ?, projectMethodology = ?, "
        + "projectResources = ?, midtermProjectObjectives = ?,generalProjectObjectives = ?, "
        + "inmediateProjectObjectives = ?, projectResponsabilities = ?, projectAttendantName = ?, "
        + "projectAttendantEmail = ?, projectAttendantPosition = ? WHERE projectId = ?";
    
    private static final String DELETE_QUERY = "DELETE FROM Project WHERE projectId = ?";
    
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Project WHERE projectId = ?";
    
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Project";

    public int insertProject(ProjectDto project) throws DatabaseOperationException {
        
        int generatedId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
        (INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            
            setProjectStatement(preparedStatement, project);
            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            // logger aquí
            throw new DatabaseOperationException("Error al registrar el proyecto: "
                + project.getProjectName(), e);
        }
        return generatedId;
    }

    public boolean updateProject(ProjectDto project) throws DatabaseOperationException {
        boolean updated = false;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            
            setProjectStatement(preparedStatement, project);
            preparedStatement.setInt(13, project.getProjectId());
            
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            // logger aquí
            throw new DatabaseOperationException("Error al actualizar el proyecto con ID: "
                + project.getProjectId(), e);
        }
        return updated;
    }

    public boolean deleteProject(int projectId) throws DatabaseOperationException {
        boolean deleted = false;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            
            preparedStatement.setInt(1, projectId);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            // logger aquí
            throw new DatabaseOperationException("Error al eliminar el proyecto con ID: "
                + projectId, e);
        }
        return deleted;
    }

    public ProjectDto getProjectById(int projectId) throws DatabaseOperationException {
        ProjectDto project = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            
            preparedStatement.setInt(1, projectId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    project = mapResultSetToDto(rs);
                }
            }
        } catch (SQLException e) {
            // [IMPLEMENTAR LOG AQUÍ]
            throw new DatabaseOperationException("Error al buscar el proyecto con ID: "
                + projectId, e);
        }
        return project;
    }

    public List<ProjectDto> getAllProjects() throws DatabaseOperationException {
        List<ProjectDto> projects = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = preparedStatement.executeQuery()) {
            
            while (rs.next()) {
                projects.add(mapResultSetToDto(rs));
            }
        } catch (SQLException e) {
            // logger aquí
            throw new DatabaseOperationException("Error al obtener la lista de proyectos", e);
        }
        return projects;
    }
    
    private void setProjectStatement(PreparedStatement preparedStatement, ProjectDto project) throws SQLException {
        preparedStatement.setInt(1, project.getLinkedOrganizationId());
        preparedStatement.setString(2, project.getProjectName());
        preparedStatement.setString(3, project.getProjectDescription());
        preparedStatement.setString(4, project.getProjectMethodology());
        preparedStatement.setString(5, project.getProjectResources());
        preparedStatement.setString(6, project.getMidtermProjectObjectives());
        preparedStatement.setString(7, project.getGeneralProjectObjectives());
        preparedStatement.setString(8, project.getInmediateProjectObjectives());
        preparedStatement.setString(9, project.getProjectResponsabilities());
        preparedStatement.setString(10, project.getProjectAttendantName());
        preparedStatement.setString(11, project.getProjectAttendantEmail());
        preparedStatement.setString(12, project.getProjectAttendantPosition());
    }

    private ProjectDto mapResultSetToDto(ResultSet rs) throws SQLException {
        return new ProjectDto(
            rs.getInt("projectId"),
            rs.getInt("linkedOrganizationId"),
            rs.getString("projectName"),
            rs.getString("projectDescription"),
            rs.getString("projectMethodology"),
            rs.getString("projectResources"),
            rs.getString("midtermProjectObjectives"),
            rs.getString("generalProjectObjectives"),
            rs.getString("inmediateProjectObjectives"),
            rs.getString("projectResponsabilities"),
            rs.getString("projectAttendantName"),
            rs.getString("projectAttendantEmail"),
            rs.getString("projectAttendantPosition")
        );
    }
}
