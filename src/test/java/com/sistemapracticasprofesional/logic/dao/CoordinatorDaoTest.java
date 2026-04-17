package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinatorDaoTest {

    private static final int TEST_PERSONNEL_NUMBER = 920001;
    private static final int TEST_USER_ID = 920101;

    private CoordinatorDao coordinatorDao;
    private UserDao userDao;
    private int testPersonnelNumber;
    private int testUserId;

    @BeforeEach
    public void setUp() {
        coordinatorDao = new CoordinatorDao();
        userDao = new UserDao();
        testPersonnelNumber = TEST_PERSONNEL_NUMBER;
        testUserId = TEST_USER_ID;

        if (userDao.getUser(testUserId) == null) {
            UserDto user = new UserDto(testUserId, "usuario_prueba_coordinator", "password123");
            userDao.insertUser(user);
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            coordinatorDao.deleteCoord(testUserId);
        } catch (DaoException daoException) {
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Test
    public void testRegisterCoordinator() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Coordinador Prueba",
                "Activo",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );

        boolean result = coordinatorDao.insertCoordinator(coordinator);
        CoordinatorDto savedCoordinator = coordinatorDao.getCoordinatorById(testUserId);

        assertTrue(result);
        assertNotNull(savedCoordinator);
        assertEquals(testUserId, savedCoordinator.getUserId());
        assertEquals("Coordinador Prueba", savedCoordinator.getName());
        assertEquals("Activo", savedCoordinator.getState());
        assertEquals(LocalDate.of(2026, 4, 1), savedCoordinator.getEntryDate());
        assertEquals(LocalDate.of(2026, 12, 31), savedCoordinator.getExitDate());
    }

    @Test
    public void testUpdateCoordinator() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Coordinador Inicial",
                "Activo",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );
        coordinatorDao.insertCoordinator(coordinator);

        coordinator.setName("Coordinador Actualizado");
        coordinator.setState("Inactivo");
        coordinator.setEntryDate(LocalDate.of(2026, 5, 1));
        coordinator.setExitDate(LocalDate.of(2027, 1, 31));

        boolean result = coordinatorDao.updateCoordinator(coordinator);
        CoordinatorDto updatedCoordinator = coordinatorDao.getCoordinatorById(testUserId);

        assertTrue(result);
        assertNotNull(updatedCoordinator);
        assertEquals("Coordinador Actualizado", updatedCoordinator.getName());
        assertEquals("Inactivo", updatedCoordinator.getState());
        assertEquals(LocalDate.of(2026, 5, 1), updatedCoordinator.getEntryDate());
        assertEquals(LocalDate.of(2027, 1, 31), updatedCoordinator.getExitDate());
    }

    @Test
    public void testDeleteCoord() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Coordinador Eliminar",
                "Activo",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );
        coordinatorDao.insertCoordinator(coordinator);

        boolean result = coordinatorDao.deleteCoord(testUserId);
        CoordinatorDto deletedCoordinator = coordinatorDao.getCoordinatorById(testUserId);

        assertTrue(result);
        assertNull(deletedCoordinator);
    }

    @Test
    public void testGetCoordinatorById() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Coordinador Buscar",
                "Activo",
                LocalDate.of(2026, 2, 15),
                LocalDate.of(2026, 8, 15)
        );
        coordinatorDao.insertCoordinator(coordinator);

        CoordinatorDto foundCoordinator = coordinatorDao.getCoordinatorById(testUserId);

        assertNotNull(foundCoordinator);
        assertEquals(testUserId, foundCoordinator.getUserId());
        assertEquals("Coordinador Buscar", foundCoordinator.getName());
        assertEquals("Activo", foundCoordinator.getState());
        assertEquals(LocalDate.of(2026, 2, 15), foundCoordinator.getEntryDate());
        assertEquals(LocalDate.of(2026, 8, 15), foundCoordinator.getExitDate());
    }

    @Test
    public void testGetAllCoordinators() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Coordinador Lista",
                "Activo",
                LocalDate.of(2026, 1, 10),
                LocalDate.of(2026, 10, 10)
        );
        coordinatorDao.insertCoordinator(coordinator);

        List<CoordinatorDto> coordinators = coordinatorDao.getAllCoordinators();
        boolean containsTestCoordinator = coordinators.stream()
                .anyMatch(savedCoordinator -> testUserId == savedCoordinator.getUserId());

        assertNotNull(coordinators);
        assertFalse(coordinators.isEmpty());
        assertTrue(containsTestCoordinator);
    }
}
