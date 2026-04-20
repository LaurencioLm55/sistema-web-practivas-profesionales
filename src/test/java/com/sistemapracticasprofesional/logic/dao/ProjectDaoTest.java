package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
                    testOrganizationId, "Organizacion Prueba Proyecto", 
                    "Direccion de prueba", "Tecnologia", "Xalapa",
                    "Veracruz", "2281234567", "organizacion.proyecto@example.com" );
            
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
    public void testInsertProjectSuccessfull() {
        ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "Proyecto Prueba", 
                "Descripcion de prueba", "SCRUM", "Equipo y software", "Objetivos medios", 
                "Objetivo general", "Objetivos inmediatos", "Responsabilidades de prueba", "Ana Garcia",
                "ana@example.com", "Lunes a Viernes" );

        boolean result = projectDao.insertProject(projectTest);

        assertEquals(projectTest, projectDao.getProjectById(testProjectId));
    }

    @Test
    public void testUpdateProjectSuccessfull() {
    ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "Proyecto Inicial", 
            "Descripcion inicial", "Cascada", "Recursos iniciales", "Objetivos iniciales", 
            "Objetivo inicial", "Inmediatos iniciales", "Responsabilidades iniciales", "Luis Perez",
            "luis@example.com", "Martes y Jueves"
    );
    projectDao.insertProject(projectTest);

    projectTest.setProjectName("Proyecto Actualizado");
    
    assertTrue(projectDao.updateProject(projectTest));
}


    @Test
    public void testDeleteProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto( testProjectId, testOrganizationId, 
                "Proyecto Eliminar", "Descripcion", "SCRUM", "Recursos", "Objetivos medios", 
                "Objetivo general", "Objetivos inmediatos", "Responsabilidades", "Mario Diaz",
                "mario@example.com", "Lunes" );

        projectDao.insertProject(projectTest);

        boolean result = projectDao.deleteProject(testProjectId);

        assertNull(projectDao.getProjectById(testProjectId));
    }

    @Test
    public void testGetProjectSuccessfully() {
        ProjectDto projectTest = new ProjectDto( testProjectId, testOrganizationId, "Proyecto Buscar", 
                "Descripcion buscar", "XP", "Recursos buscar", "Objetivos medios buscar", 
                "Objetivo general buscar", "Objetivos inmediatos buscar", "Responsabilidades buscar",
                "Rosa Vega", "rosa@example.com", "Miercoles" );
        projectDao.insertProject(projectTest);

        assertEquals(projectTest, projectDao.getProjectById(testProjectId));
    }

    @Test
    public void testListProyectsSuccessfully() {
        ProjectDto projectTest = new ProjectDto(testProjectId, testOrganizationId, "Proyecto Lista", 
                "Descripcion lista", "SCRUM", "Recursos lista", "Objetivos medios lista",
                "Objetivo general lista", "Objetivos inmediatos lista", "Responsabilidades lista", 
                "Elena Cruz", "elena@example.com", "Jueves"
        );
        projectDao.insertProject(projectTest);

        List<ProjectDto> listProjects = projectDao.getAllProjects();

        assertTrue(listProjects.contains(projectTest));
    }

    @Test
    public void testInsertProjectNoSuccessfully(){
        
        ProjectDto projectTest = new ProjectDto(testProjectId, 999999, "Proyecto Lista",
                "Descripcion lista", "SCRUM", "Recursos lista", "Objetivos medios lista",
                "Objetivo general lista", "Objetivos inmediatos lista", "Responsabilidades lista", 
                "Elena Cruz", "elena@example.com", "Jueves"
        );
        
        assertThrows(DaoException.class, () -> projectDao.insertProject(projectTest));

    }


}
