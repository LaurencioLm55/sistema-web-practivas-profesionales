package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import java.sql.SQLException;
import java.util.List;
public interface IAffiliatedOrganization {
    boolean registredOrganization(AffiliatedOrganizationDto affiliatedOrganization) throws SQLException;
    boolean updateData(String nameData, String data, int idAffiliatedOrganization) throws SQLException;
    AffiliatedOrganizationDto getAffiliatedOrganization(int idAffilitedOrganization) throws SQLException;
    List<AffiliatedOrganizationDto> getListAffiliatedOrganiztion(String data) throws SQLException;
}
