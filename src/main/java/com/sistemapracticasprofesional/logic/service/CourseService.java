package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.CourseDao;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class CourseService {

    private final CourseDao courseDao = new CourseDao();

    public void registerCourse(String nrcText, String staffNumberText, String status,
                               String period, String section ) throws ValidationException, ServiceException {

        CourseDto course = buildCourse(nrcText, staffNumberText, status, period, section);

        try {
            boolean registered = courseDao.registerCourse(course);

            if (!registered) {
                throw new ServiceException("The course could not be registered.", null);
            }

        } catch (DaoException e) {
            throw new ServiceException("An error occurred while registering the course.", e);
        }
    }

    private CourseDto buildCourse(String nrcText, String staffNumberText, String status,
                                 String period, String section) throws ValidationException {

        if (isBlank(nrcText) || isBlank(staffNumberText)
                || isBlank(status) || isBlank(period) || isBlank(section)) {
            throw new ValidationException("All fields are required.");
        }

        int nrc = parsePositiveInteger(nrcText, "The NRC must be numeric and greater than zero.");
        int staffNumber = parsePositiveInteger(staffNumberText, "The staff number must be numeric and greater than zero.");

        CourseDto course = new CourseDto();
        course.setNrc(nrc);
        course.setStaffNumber(staffNumber);
        course.setStatus(status.trim());
        course.setPeriod(period.trim());
        course.setSection(section.trim());
        course.setFormatFile(null);

        return course;
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    private int parsePositiveInteger(String text, String message) throws ValidationException {
        try {
            int value = Integer.parseInt(text.trim());

            if (value <= 0) {
                throw new ValidationException(message);
            }

            return value;
        } catch (NumberFormatException e) {
            throw new ValidationException(message);
        }
    }
}
