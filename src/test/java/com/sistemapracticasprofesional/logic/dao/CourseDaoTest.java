package com.sistemapracticasprofesional.logic.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.sistemapracticasprofesional.logic.dto.CourseDto;


public class CourseDaoTest {
    
    private CourseDao courseDao;

    @BeforeEach
    void initializaDao(){
        courseDao = new CourseDao();
    }

    @Test
    public void testRegisterCourse() {
        CourseDto courseDto = new CourseDto(40776, 13135, "activo", "01-26", "2", "Archivo.txt");
        boolean success = courseDao.registerCourse(courseDto);
        
        assertTrue(success);
        
    }

    @Test
    public void testUpdateCourse() {
        CourseDto courseDto = new CourseDto(40776, 13135, "inactivo", "01-26", "2", "Archivo.txt");
        boolean success = courseDao.updateCourse(courseDto);
        
        assertTrue(success);
    }

    @Test
    public void testGetCourse() {
        CourseDto databaseCourseDto = courseDao.getCourse(40776);
        CourseDto courseDto = new CourseDto(40776, 13135, "inactivo", "01-26", "2", "Archivo.txt");
        
        assertEquals(databaseCourseDto .getStaffNumber() , courseDto.getStaffNumber());
        
    }

    @Test
    public void testGetListCourse() {

        List<CourseDto> listCourses = courseDao.getCoursesByStatus("activo");

        assertEquals(4, listCourses.size());
        
    }
    
}
