package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface ICourse {
    
    boolean registerCourse(CourseDto course) throws DaoException;
    boolean updateCourse(String data, String newData , int nrc) throws DaoException;
    CourseDto getCourse(int nrc) throws DaoException;
    List<CourseDto> getListCourse(String filter) throws DaoException;
    
    
    
}
