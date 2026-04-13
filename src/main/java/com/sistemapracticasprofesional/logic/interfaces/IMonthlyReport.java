package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IMonthlyReport {
    boolean registredReport(MonthlyReportDto monthlyReport) throws DaoException;
    boolean updateMonthlyReport(String data, String newData, int idMonthlyReport) throws DaoException;
    MonthlyReportDto getMonthlyReport(int idMonthlyReport) throws DaoException;
    List<MonthlyReportDto> getListMonthlyReport(String idIntern) throws DaoException;
}
