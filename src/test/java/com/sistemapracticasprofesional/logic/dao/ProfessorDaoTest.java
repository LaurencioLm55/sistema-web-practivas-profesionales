package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessorDaoTest {

    private static final int TEST_STAFF_NUMBER = 910001;

    private ProfessorDao professorDao;
    private int testStaffNumber;

    @BeforeEach
    public void setUp() {
        professorDao = new ProfessorDao();
        testStaffNumber = TEST_STAFF_NUMBER;
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
    public void testInsertProfessorSuccessfully() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Prueba", "Matutino");

        assertTrue(professorDao.insertProfessor(professor));
    }

    @Test
    public void testUpdateProfessorSuccessfully() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Inicial", "Matutino");
        professorDao.insertProfessor(professor);

        professor.setName("Profesor Actualizado");
        professor.setShift("Vespertino");

        assertTrue(professorDao.updateProfessor(professor));
    }

    @Test
    public void testDeleteProfessorSuccessfully() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Eliminar", "Matutino");
        professorDao.insertProfessor(professor);

        assertTrue(professorDao.deleteProfessor(testStaffNumber));
    }

    @Test
    public void testGetProfessorByStaffNumberSuccessfully() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Buscar", "Mixto");
        professorDao.insertProfessor(professor);

        assertNotNull(professorDao.getProfessorByStaffNumber(testStaffNumber));
    }

    @Test
    public void testGetAllProfessorsSuccessfully() {
        ProfessorDto professor = new ProfessorDto(testStaffNumber, null, "Profesor Lista", "Matutino");
        professorDao.insertProfessor(professor);

        List<ProfessorDto> professors = professorDao.getAllProfessors();

        assertNotNull(professors);
    }
    
}
