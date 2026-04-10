package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import java.util.List;

public interface ICourse {
    
    boolean registerCourse(CourseDto course);
    boolean updateCourse(String data, String newData , int nrc);
    CourseDto getCourse(int nrc); 
    List<CourseDto> getListCourse(String filtrer);
    
    
    
}
