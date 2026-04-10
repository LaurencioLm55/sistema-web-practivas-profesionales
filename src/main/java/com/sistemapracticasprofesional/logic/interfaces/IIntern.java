package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import java.util.List;

public interface IIntern {

    boolean insertIntern(InternDto intern);

    boolean updateIntern(InternDto intern);

    boolean deleteIntern(String matricula);

    InternDto getInternByMatricula(String matricula);

    List<InternDto> getAllInterns();
}