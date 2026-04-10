package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import java.util.List;

public interface IProfessor {

    boolean insertProfessor(ProfessorDto professor);

    boolean updateProfessor(ProfessorDto professor);

    boolean deleteProfessor(int staffNumber);

    ProfessorDto getProfessorByStaffNumber(int staffNumber);

    List<ProfessorDto> getAllProfessors();
    
}

