package com.sistemapracticasprofesional.logic.dao;

import com.sistemapracticasprofesional.dataaccess.DatabaseConnection;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectDaoTest {

    private ProjectDao projectDao;
    private AffiliatedOrganizationDao affiliatedOrganizationDao;
    private int testProjectId;
    private int testOrganizationId;

    @BeforeEach
    public void setUp() {
        projectDao = new ProjectDao();
        affiliatedOrganizationDao = new AffiliatedOrganizationDao();
        testProjectId = (int) (System.currentTimeMillis() % 1000000);
        testOrganizationId = testProjectId + 500;
        AffiliatedOrganizationDto organization = new AffiliatedOrganizationDto(
                testOrganizationId,
                "Organizacion " + testOrganizationId,
                "Direccion de prueba",
                "Tecnologia",
                "Xalapa",
                "Veracruz",
                "2281234567",
                "organizacion" + testOrganizationId + "@example.com"
        );
        affiliatedOrganizationDao.insertOrganization(organization);
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
    public void testInsertProject() {
        ProjectDto project = new ProjectDto(
                testProjectId,
                testOrganizationId,
                "Proyecto Prueba",
                "Descripcion de prueba",
                "SCRUM",
                "Equipo y software",
                "Objetivos medios",
                "Objetivo general",
                "Objetivos inmediatos",
                "Responsabilidades de prueba",
                "Ana Garcia",
                "ana@example.com",
                "Lunes a Viernes"
        );

        boolean result = projectDao.insertProject(project);
        ProjectDto savedProject = projectDao.getProject(testProjectId);

        assertTrue(result);
        assertNotNull(savedProject);
        assertEquals(testProjectId, savedProject.getProjectId());
        assertEquals(testOrganizationId, savedProject.getLinkedOrganizationId());
        assertEquals("Proyecto Prueba", savedProject.getProjectName());
        assertEquals("Descripcion de prueba", savedProject.getProjectDescription());
        assertEquals("SCRUM", savedProject.getProjectMethodology());
        assertEquals("Equipo y software", savedProject.getProjectResources());
        assertEquals("Objetivos medios", savedProject.getMidtermProjectObjectives());
        assertEquals("Objetivo general", savedProject.getGeneralProjectObjectives());
        assertEquals("Objetivos inmediatos", savedProject.getInmediateProjectObjectives());
        assertEquals("Responsabilidades de prueba", savedProject.getProjectResponsabilities());
        assertEquals("Ana Garcia", savedProject.getProjectAttendantName());
        assertEquals("ana@example.com", savedProject.getProjectAttendantEmail());
        assertEquals("Lunes a Viernes", savedProject.getProjectAttendantPosition());
    }

    @Test
    public void testUpdateProject() {
        ProjectDto project = new ProjectDto(
                testProjectId,
                testOrganizationId,
                "Proyecto Inicial",
                "Descripcion inicial",
                "Cascada",
                "Recursos iniciales",
                "Objetivos iniciales",
                "Objetivo inicial",
                "Inmediatos iniciales",
                "Responsabilidades iniciales",
                "Luis Perez",
                "luis@example.com",
                "Martes y Jueves"
        );
        projectDao.insertProject(project);

        project.setProjectName("Proyecto Actualizado");
        project.setProjectDescription("Descripcion actualizada");
        project.setProjectMethodology("Kanban");
        project.setProjectResources("Recursos actualizados");
        project.setMidtermProjectObjectives("Objetivos medios actualizados");
        project.setGeneralProjectObjectives("Objetivo general actualizado");
        project.setInmediateProjectObjectives("Objetivos inmediatos actualizados");
        project.setProjectResponsabilities("Responsabilidades actualizadas");
        project.setProjectAttendantName("Laura Mendoza");
        project.setProjectAttendantEmail("laura@example.com");
        project.setProjectAttendantSchedule("Viernes");

        boolean result = projectDao.updateProject(project);
        ProjectDto updatedProject = projectDao.getProject(testProjectId);

        assertTrue(result);
        assertNotNull(updatedProject);
        assertEquals("Proyecto Actualizado", updatedProject.getProjectName());
        assertEquals("Descripcion actualizada", updatedProject.getProjectDescription());
        assertEquals("Kanban", updatedProject.getProjectMethodology());
        assertEquals("Recursos actualizados", updatedProject.getProjectResources());
        assertEquals("Objetivos medios actualizados", updatedProject.getMidtermProjectObjectives());
        assertEquals("Objetivo general actualizado", updatedProject.getGeneralProjectObjectives());
        assertEquals("Objetivos inmediatos actualizados", updatedProject.getInmediateProjectObjectives());
        assertEquals("Responsabilidades actualizadas", updatedProject.getProjectResponsabilities());
        assertEquals("Laura Mendoza", updatedProject.getProjectAttendantName());
        assertEquals("laura@example.com", updatedProject.getProjectAttendantEmail());
        assertEquals("Viernes", updatedProject.getProjectAttendantPosition());
    }

    @Test
    public void testDeleteProject() {
        ProjectDto project = new ProjectDto(
                testProjectId,
                testOrganizationId,
                "Proyecto Eliminar",
                "Descripcion",
                "SCRUM",
                "Recursos",
                "Objetivos medios",
                "Objetivo general",
                "Objetivos inmediatos",
                "Responsabilidades",
                "Mario Diaz",
                "mario@example.com",
                "Lunes"
        );
        projectDao.insertProject(project);

        boolean result = projectDao.deleteProject(testProjectId);
        ProjectDto deletedProject = projectDao.getProject(testProjectId);

        assertTrue(result);
        assertNull(deletedProject);
    }

    @Test
    public void testGetProject() {
        ProjectDto project = new ProjectDto(
                testProjectId,
                testOrganizationId,
                "Proyecto Buscar",
                "Descripcion buscar",
                "XP",
                "Recursos buscar",
                "Objetivos medios buscar",
                "Objetivo general buscar",
                "Objetivos inmediatos buscar",
                "Responsabilidades buscar",
                "Rosa Vega",
                "rosa@example.com",
                "Miercoles"
        );
        projectDao.insertProject(project);

        ProjectDto foundProject = projectDao.getProject(testProjectId);

        assertNotNull(foundProject);
        assertEquals(testProjectId, foundProject.getProjectId());
        assertEquals(testOrganizationId, foundProject.getLinkedOrganizationId());
        assertEquals("Proyecto Buscar", foundProject.getProjectName());
    }

    @Test
    public void testListProyects() {
        ProjectDto project = new ProjectDto(
                testProjectId,
                testOrganizationId,
                "Proyecto Lista",
                "Descripcion lista",
                "SCRUM",
                "Recursos lista",
                "Objetivos medios lista",
                "Objetivo general lista",
                "Objetivos inmediatos lista",
                "Responsabilidades lista",
                "Elena Cruz",
                "elena@example.com",
                "Jueves"
        );
        projectDao.insertProject(project);

        List<ProjectDto> projects = projectDao.listProyects();
        boolean containsTestProject = projects.stream()
                .anyMatch(savedProject -> savedProject.getProjectId() == testProjectId);

        assertNotNull(projects);
        assertFalse(projects.isEmpty());
        assertTrue(containsTestProject);
    }
}
