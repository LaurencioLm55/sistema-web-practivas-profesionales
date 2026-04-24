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
                throw new ServiceException("No se pudo registrar el curso.", null);
            }

        } catch (DaoException e) {
            throw new ServiceException("Error al registrar el curso.", e);
        }
    }

    private CourseDto buildCourse(String nrcText, String staffNumberText, String status,
                                 String period, String section) throws ValidationException {

        if (isBlank(nrcText) || isBlank(staffNumberText)
                || isBlank(status) || isBlank(period) || isBlank(section)) {
            throw new ValidationException("Todos los campos son obligatorios.");
        }

        int nrc = parsePositiveInteger(nrcText, "El NRC debe ser numérico y mayor a cero.");
        int staffNumber = parsePositiveInteger(staffNumberText, "El número de personal debe ser numérico y mayor a cero.");

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