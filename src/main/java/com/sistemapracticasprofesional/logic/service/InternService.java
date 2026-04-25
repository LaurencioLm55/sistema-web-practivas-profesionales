package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class InternService {

    private final InternDao internDao = new InternDao();

    public void registerIntern(String studentId, String ageText, String name,
                               String indigenousLanguage, String gender, String major)
                                throws ValidationException, ServiceException {

        InternDto intern = buildIntern(studentId, ageText, name, indigenousLanguage, gender, major);

        try {
            boolean registered = internDao.insertIntern(intern);

            if (!registered) {
                throw new ServiceException("The intern could not be registered.", null);
            }

        } catch (DaoException e) {
            throw new ServiceException("An error occurred while registering the intern.", e);
        }
    }

    private InternDto buildIntern(String studentId, String ageText, String name,
                                  String indigenousLanguage, String gender, String major)
            throws ValidationException {

        if (isBlank(studentId) || isBlank(ageText) || isBlank(name)
                || isBlank(gender) || isBlank(major)) {
            throw new ValidationException("Student ID, age, name, gender, and major are required.");
        }

        int age = parsePositiveInteger(ageText, "Age must be numeric and greater than zero.");
        String normalizedLanguage = isBlank(indigenousLanguage) ? null : indigenousLanguage.trim();

        return new InternDto(
                studentId.trim(),
                age,
                name.trim(),
                normalizedLanguage,
                gender.trim(),
                major.trim()
        );
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
