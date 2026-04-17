package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessorDaoTest {

    private ProfessorDao professorDao;
    private int testStaffNumber;

    @BeforeEach
    public void setUp() {
        professorDao = new ProfessorDao();
        testStaffNumber = (int) (System.currentTimeMillis() % 1000000);
    }

    @AfterEach
    public void tearDown() {
        try {
            professorDao.deleteProfessor(testStaffNumber);
            
        } catch (DaoException daoException) {
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Test
    public void testInsertProfessor() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Prueba", "Matutino");

        boolean result = professorDao.insertProfessor(professor);
        ProfessorDto savedProfessor = professorDao.getProfessorByStaffNumber(testStaffNumber);

        assertTrue(result);
        assertNotNull(savedProfessor);
        assertEquals(testStaffNumber, savedProfessor.getStaffNumber());
        assertNull(savedProfessor.getUserId());
        assertEquals("Profesor Prueba", savedProfessor.getName());
        assertEquals("Matutino", savedProfessor.getShift());
    }

    @Test
    public void testUpdateProfessor() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Inicial", "Matutino");
        professorDao.insertProfessor(professor);

        professor.setName("Profesor Actualizado");
        professor.setShift("Vespertino");

        boolean result = professorDao.updateProfessor(professor);
        ProfessorDto updatedProfessor = professorDao.getProfessorByStaffNumber(testStaffNumber);

        assertTrue(result);
        assertNotNull(updatedProfessor);
        assertEquals("Profesor Actualizado", updatedProfessor.getName());
        assertEquals("Vespertino", updatedProfessor.getShift());
    }

    @Test
    public void testDeleteProfessor() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Eliminar", "Matutino");
        professorDao.insertProfessor(professor);

        boolean result = professorDao.deleteProfessor(testStaffNumber);
        ProfessorDto deletedProfessor = professorDao.getProfessorByStaffNumber(testStaffNumber);

        assertTrue(result);
        assertNull(deletedProfessor);
    }

    @Test
    public void testGetProfessorByStaffNumber() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Buscar", "Mixto");
        professorDao.insertProfessor(professor);

        ProfessorDto foundProfessor = professorDao.getProfessorByStaffNumber(testStaffNumber);

        assertNotNull(foundProfessor);
        assertEquals(testStaffNumber, foundProfessor.getStaffNumber());
        assertEquals("Profesor Buscar", foundProfessor.getName());
        assertEquals("Mixto", foundProfessor.getShift());
    }

    @Test
    public void testGetAllProfessors() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Lista", "Matutino");
        professorDao.insertProfessor(professor);

        List<ProfessorDto> professors = professorDao.getAllProfessors();
        boolean containsTestProfessor = professors.stream()
                .anyMatch(savedProfessor -> savedProfessor.getStaffNumber() == testStaffNumber);

        assertNotNull(professors);
        assertFalse(professors.isEmpty());
        assertTrue(containsTestProfessor);
    }
    
}
