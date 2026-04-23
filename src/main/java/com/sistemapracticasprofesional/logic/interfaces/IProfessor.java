package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IProfessor {

    boolean insertProfessor(ProfessorDto professor) throws DaoException;

    boolean updateProfessor(ProfessorDto professor) throws DaoException;

    boolean deleteProfessor(int staffNumber) throws DaoException;

    ProfessorDto getProfessorByStaffNumber(int staffNumber) throws DaoException;

    List<ProfessorDto> getAllProfessors() throws DaoException;
    
}

