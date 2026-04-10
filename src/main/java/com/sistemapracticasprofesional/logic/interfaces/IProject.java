package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import java.util.List;
import java.sql.SQLException;

public interface IProject {
    boolean registredProject(ProjectDto project) throws SQLException;
    boolean updateDataProject(String data, String newData) throws SQLException;
    ProjectDto getProject(int idProject) throws SQLException;
    List<ProjectDto> serchProject(String filter) throws SQLException;
}
