package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IIntern {

    boolean insertIntern(InternDto intern) throws DaoException;

    boolean updateIntern(InternDto intern) throws DaoException;

    boolean deleteIntern(String matricula) throws DaoException;

    InternDto getInternByMatricula(String matricula) throws DaoException;

    List<InternDto> getAllInterns() throws DaoException;
}
