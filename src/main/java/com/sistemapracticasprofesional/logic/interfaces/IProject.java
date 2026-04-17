package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IProject {
    
    boolean insertProject(ProjectDto project) throws DaoException;
    boolean updateProject(ProjectDto project) throws DaoException;
    ProjectDto getProjectById(int idProject) throws DaoException;
    List<ProjectDto> getAllProjects() throws DaoException;
    
}
