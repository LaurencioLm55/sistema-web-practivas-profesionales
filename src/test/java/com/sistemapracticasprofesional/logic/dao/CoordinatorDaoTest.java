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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
            UserDto user = new UserDto(testUserId, "coordinator_test_user", "password123");
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
    public void testRegisterCoordinatorSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Test Coordinator",
                "Active",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );

        assertTrue(coordinatorDao.insertCoordinator(coordinator));
    }

    @Test
    public void testUpdateCoordinatorSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Initial Coordinator",
                "Active",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );
        coordinatorDao.insertCoordinator(coordinator);

        coordinator.setName("Updated Coordinator");
        coordinator.setState("Inactive");
        coordinator.setEntryDate(LocalDate.of(2026, 5, 1));
        coordinator.setExitDate(LocalDate.of(2027, 1, 31));

        assertTrue(coordinatorDao.updateCoordinator(coordinator));
    }

    @Test
    public void testDeleteCoordSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Delete Coordinator",
                "Active",
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 12, 31)
        );
        coordinatorDao.insertCoordinator(coordinator);

        assertTrue(coordinatorDao.deleteCoord(testUserId));
    }

    @Test
    public void testGetCoordinatorByIdSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "Search Coordinator",
                "Active",
                LocalDate.of(2026, 2, 15),
                LocalDate.of(2026, 8, 15)
        );
        coordinatorDao.insertCoordinator(coordinator);

        assertNotNull(coordinatorDao.getCoordinatorById(testUserId));
    }

    @Test
    public void testGetAllCoordinatorsSuccessfully() {
        CoordinatorDto coordinator = new CoordinatorDto(
                testPersonnelNumber,
                testUserId,
                "List Coordinator",
                "Active",
                LocalDate.of(2026, 1, 10),
                LocalDate.of(2026, 10, 10)
        );
        coordinatorDao.insertCoordinator(coordinator);

        List<CoordinatorDto> coordinators = coordinatorDao.getAllCoordinators();

        assertNotNull(coordinators);
    }
}
