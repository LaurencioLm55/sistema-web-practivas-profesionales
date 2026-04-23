package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface ICoordinator {
    public boolean insertCoordinator(CoordinatorDto coordinator) throws DaoException;
    public boolean updateCoordinator(CoordinatorDto coordinator) throws DaoException;
    public boolean deleteCoord(int userId) throws DaoException;
    public CoordinatorDto getCoordinatorById(int id) throws DaoException;
    public List<CoordinatorDto> getAllCoordinators() throws DaoException;
}
