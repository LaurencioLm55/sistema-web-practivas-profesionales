package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import java.sql.SQLException;
import java.util.List;

public interface IMonthlyReportDao {
    boolean registredReport(MonthlyReportDto monthlyReport) throws SQLException;
    boolean updateMonthlyReport(String data, String newData, int idMonthlyReport) throws SQLException;
    MonthlyReportDto getMonthlyReport(int idMonthlyReport) throws SQLException;
    List<MonthlyReportDto> getListMonthlyReport(String idIntern)throws SQLException;
}
