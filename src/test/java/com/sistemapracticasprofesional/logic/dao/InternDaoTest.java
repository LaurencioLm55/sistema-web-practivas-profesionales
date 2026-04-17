package com.sistemapracticasprofesional.logic.dao;

import org.junit.jupiter.api.Test;

import com.sistemapracticasprofesional.logic.dto.InternDto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
        InternDto internDto = new InternDto("S24060080", 20, "Rodrigo Montoya Herrera", "No", "Masculino", "Ingenieria de Software");
        boolean success = internDao.insertIntern(internDto);
        assertTrue( success );
    }

    @Test
    public void testUpdateIntern() {
        InternDto internDto = new InternDto("S24060080", 20, "Sebastián Vargas Ríos", "No", "Masculino", "Ingenieria de Software");
        boolean success = internDao.updateIntern(internDto);
        assertTrue( success );
    }

    @Test
    public void testGetInternByMatricula() {
        InternDto internDto = internDao.getInternByMatricula("S24060080");
        assertEquals( "Sebastián Vargas Ríos", internDto.getNombre());
    }

    @Test
    public void testGetAllInterns() {
        List<InternDto> listInterns = internDao.getAllInterns();

        assertEquals(3, listInterns.size());
    }

    @Test
    public void testDeleteIntern() {
        boolean success = internDao.deleteIntern("S24060080");
        assertTrue( success );
    }
    
}
