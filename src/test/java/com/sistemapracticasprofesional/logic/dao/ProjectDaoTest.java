package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectDaoTest {

    private static final int TEST_PROJECT_ID = 930001;
    private static final int TEST_ORGANIZATION_ID = 930101;
    private ProjectDao projectDao;
    private AffiliatedOrganizationDao affiliatedOrganizationDao;
    private int testProjectId;
    private int testOrganizationId;

    @BeforeEach
    public void setUp() {
        projectDao = new ProjectDao();
        affiliatedOrganizationDao = new AffiliatedOrganizationDao();
        testProjectId = TEST_PROJECT_ID;
        testOrganizationId = TEST_ORGANIZATION_ID;

        AffiliatedOrganizationDto existingOrganization = 
                affiliatedOrganizationDao.getAffiliatedOrganization(testOrganizationId);

        if (existingOrganization.getName() == null) {
            AffiliatedOrganizationDto organization = new AffiliatedOrganizationDto(
                    testOrganizationId, "Project Test Organization", 
                    "Test Address", "Technology", "Xalapa",
                    "Veracruz", "2281234567", "project.organization@example.com" );
            
            affiliatedOrganizationDao.insertOrganization(organization);
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            projectDao.deleteProject(testProjectId);
        } catch (DaoException daoException) {
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Test
    public void testInsertProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "Test Project", 
                "Test Description", "SCRUM", "Equipment and Software", "Midterm Objectives", 
                "General Objective", "Immediate Objectives", "Test Responsibilities", "Ana Garcia",
                "ana@example.com", "Monday to Friday" );

        assertTrue(projectDao.insertProject(projectTest));
    }

    @Test
    public void testUpdateProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "Initial Project", 
                "Initial Description", "Waterfall", "Initial Resources", "Initial Objectives", 
                "Initial Objective", "Initial Immediate Objectives", "Initial Responsibilities", "Luis Perez",
                "luis@example.com", "Tuesday and Thursday"
        );
        projectDao.insertProject(projectTest);

        projectTest.setProjectName("Updated Project");
        
        boolean success = projectDao.updateProject(projectTest);
        
        assertTrue(success);
    }


    @Test
    public void testDeleteProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto( testProjectId, testOrganizationId, 
                "Delete Project", "Description", "SCRUM", "Resources", "Midterm Objectives", 
                "General Objective", "Immediate Objectives", "Responsibilities", "Mario Diaz",
                "mario@example.com", "Monday" );

        projectDao.insertProject(projectTest);

        boolean result = projectDao.deleteProject(testProjectId);

        assertNull(projectDao.getProjectById(testProjectId));
    }

    @Test
    public void testGetProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto( testProjectId, testOrganizationId, "Search Project", 
                "Search Description", "XP", "Search Resources", "Search Midterm Objectives", 
                "Search General Objective", "Search Immediate Objectives", "Search Responsibilities",
                "Rosa Vega", "rosa@example.com", "Wednesday" );
        projectDao.insertProject(projectTest);

        ProjectDto success = projectDao.getProjectById(testProjectId);

        assertNotNull(success);
    }

    @Test
    public void testListProjectsSuccessfully() {
        ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "List Project", 
                "List Description", "SCRUM", "List Resources", "List Midterm Objectives",
                "List General Objective", "List Immediate Objectives", "List Responsibilities", 
                "Elena Cruz", "elena@example.com", "Thursday"
        );
        projectDao.insertProject(projectTest);

        List<ProjectDto> listProjects = projectDao.getAllProjects();
        
        assertNotNull(listProjects);
    }

    @Test
    public void testInsertProjectUnsuccessfully() {
        
        ProjectDto projectTest = new ProjectDto(testProjectId, 999999, "List Project",
                "List Description", "SCRUM", "List Resources", "List Midterm Objectives",
                "List General Objective", "List Immediate Objectives", "List Responsibilities", 
                "Elena Cruz", "elena@example.com", "Thursday"
        );
        
        assertThrows(DaoException.class, () -> projectDao.insertProject(projectTest));

    }


}
