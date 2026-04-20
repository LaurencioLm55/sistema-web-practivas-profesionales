package com.sistemapracticasprofesional.logic.dao;

import org.junit.jupiter.api.Test;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class InternDaoTest {
    
    private InternDao internDao;

    public InternDaoTest() {
    }

    @BeforeEach
    void initializaDao(){

        internDao = new InternDao();
        
    }

    @Test
    public void testInsertIntern() {

        boolean expetedResult = true;

        InternDto internDto = new InternDto("S24060080", 20, "Rodrigo Montoya Herrera", "No", "Masculino", "Ingenieria de Software");
        boolean result = internDao.insertIntern(internDto);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testUpdateIntern() {

        boolean expetedResult = true;

        InternDto internDto = new InternDto("S24060080", 20, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        boolean result = internDao.updateIntern(internDto);

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetInternByMatricula() {

        InternDto expetedResult = new InternDto("S24060080", 20, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        InternDto result = internDao.getInternByStudentId("S24060080");

        assertEquals(expetedResult, result);

    }

    @Test
    public void testGetAllInterns() {

        InternDto internDto1 = new InternDto(null, 0, null, null, null, null);
        InternDto internDto2 = new InternDto(null, 0, null, null, null, null);
        InternDto internDto3 = new InternDto(null, 0, null, null, null, null);
        InternDto internDto4 = new InternDto(null, 0, null, null, null, null);
        
        List<InternDto> expetedResult = new ArrayList<>();

        
        expetedResult.add(internDto1);
        expetedResult.add(internDto2);
        expetedResult.add(internDto3);
        expetedResult.add(internDto4);

        List<InternDto> result = internDao.getAllInterns();

        assertEquals(expetedResult, result);

    }

    @Test
    public void testDeleteIntern() {

        boolean expetedResult = true;
        boolean result = internDao.deleteIntern("S24060080");

        assertEquals(expetedResult, result);

    }
    
}
