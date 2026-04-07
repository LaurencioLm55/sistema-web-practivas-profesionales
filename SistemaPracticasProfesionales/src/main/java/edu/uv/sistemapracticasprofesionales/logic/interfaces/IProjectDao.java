package edu.uv.sistemapracticasprofesionales.logic.interfaces;
import edu.uv.sistemapracticasprofesionales.logic.dto.ProjectDto;
import java.util.List;
import java.sql.SQLException;

public interface IProjectDao {
    boolean registredProject(ProjectDto project) throws SQLException;
    boolean updateDataProject(String data, String newData) throws SQLException;
    ProjectDto getProject(int idProject) throws SQLException;
    List<ProjectDto> serchProject(String filter) throws SQLException;
}
