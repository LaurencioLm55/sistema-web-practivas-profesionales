package com.sistemapracticasprofesional.logic.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
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
    public void testRegisterCourseSuccessfully() {

        boolean expectedResult = true;

        CourseDto courseDto = new CourseDto(40776, 13135, "activo", "01-26", "2", "Archivo.txt");
        boolean realResult = courseDao.registerCourse(courseDto);
                
        assertEquals(expectedResult, realResult);

    }

    @Test
    public void testRegisterCourseUsingRepeatedMeasures() {

        boolean expectedResult = false;

        CourseDto courseDto = new CourseDto(40776, 13135, "activo", "01-26", "2", "Archivo.txt");
        boolean realResult = courseDao.registerCourse(courseDto);
                
        assertEquals(expectedResult, realResult);

    }

    
    @Test
    public void testRegisterCourseWhitEmptyfields() {

        boolean expectedResult = false;

        CourseDto courseDto = new CourseDto();
        boolean realResult = courseDao.registerCourse(courseDto);
                
        assertEquals(expectedResult, realResult);

    }

    @Test
    public void testRegisterCourseWhitInvalidData() {

        boolean expectedResult = false;

        CourseDto courseDto = new CourseDto(40, 1, "Sin estado", "23-01", "0", "Archivo.txt");
        boolean realResult = courseDao.registerCourse(courseDto);
                
        assertEquals(expectedResult, realResult);

    }

    @Test
    public void testUpdateCourseWhitValidData() {

        boolean expectedResult = true;

        CourseDto courseDto = new CourseDto(40776, 13135, "inactivo", "01-26", "2", "Archivo.txt");
        boolean realResult = courseDao.updateCourse(courseDto);
        
        assertEquals(expectedResult, realResult);

    }

    @Test
    public void testUpdateCourseWhitInvalidData() {

        boolean expectedResult = false;

        CourseDto courseDto = new CourseDto(40776, 13135, null, "01-26", "2", "Archivo.txt");
        boolean realResult = courseDao.updateCourse(courseDto);
        
        assertEquals(expectedResult, realResult);

    }


    @Test
    public void testGetCourse() {

        CourseDto realResult = courseDao.getCourse(40776);
        CourseDto expetedResult = new CourseDto(40776, 13135, "inactivo", "01-26", "2", "Archivo.txt");
        
        assertEquals(realResult, expetedResult);
        
    }



    @Test
    public void testGetListCourse() {
        
        CourseDto courseDto1 = new CourseDto(40777, 13136,  "activo",  "01-26", "1" ,"Documento.docx" );
        CourseDto courseDto2 = new CourseDto(40778, 13200, "activo", "02-26", "3", "Documento.docx");
        CourseDto courseDto3 = new CourseDto(40779, 13201, "activo", "02-26", "1", "Documento.docx");
        CourseDto courseDto4 = new CourseDto(40780, 13250, "activo", "03-26", "4", "Documento.docx");

        List<CourseDto> expetedResult = new ArrayList<>();
        expetedResult.add(courseDto1);
        expetedResult.add(courseDto2);
        expetedResult.add(courseDto3);
        expetedResult.add(courseDto4);

        List<CourseDto> realResultList = courseDao.getCoursesByStatus("activo");

        assertEquals(realResultList, expetedResult);
        
    }
    
}
