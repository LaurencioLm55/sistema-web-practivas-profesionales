package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.dto.PartialReportDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IPartialReport {
    
    boolean insertPartialReport(PartialReportDto partialReport) throws DaoException;
    boolean updatePartialReport(PartialReportDto partialReport) throws DaoException;
    boolean deletePartialReport(int idPartialReport) throws DaoException;
    PartialReportDto getPartialReportById(int idPartialReport) throws DaoException;
    List<PartialReportDto> getAllPartialReports() throws DaoException;
    
}
