package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.CourseDao;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class CourseService {

    private final CourseDao courseDao = new CourseDao();

    public void registerCourse(CourseDto courseDto) throws ValidationException, ServiceException {
        validateCourse(courseDto);

        try {
            boolean registered = courseDao.registerCourse(courseDto);

            if (!registered) {
                throw new ServiceException("The course could not be registered.");
            }

        } catch (DaoException e) {
            throw new ServiceException("An error occurred while registering the course.", e);
        }
    }

    private void validateCourse(CourseDto courseDto) throws ValidationException {
        if (courseDto == null) {
            throw new ValidationException("Course data is required.");
        }

        if (courseDto.getNrc() <= 0) {
            throw new ValidationException("The NRC must be greater than zero.");
        }

        if (courseDto.getStaffNumber() <= 0) {
            throw new ValidationException("The staff number must be greater than zero.");
        }

        if (isBlank(courseDto.getStatus())
                || isBlank(courseDto.getPeriod())
                || isBlank(courseDto.getSection())) {
            throw new ValidationException("Status, period, and section are required.");
        }

        courseDto.setStatus(courseDto.getStatus().trim());
        courseDto.setPeriod(courseDto.getPeriod().trim());
        courseDto.setSection(courseDto.getSection().trim());
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
