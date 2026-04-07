package edu.uv.sistemapracticasprofesionales.logic.interfaces;
import edu.uv.sistemapracticasprofesionales.logic.dto.AffiliatedOrganizationDto;
import java.sql.SQLException;
import java.util.List;
public interface IAffiliatedOrganizationDao {
    boolean registredOrganization(AffiliatedOrganizationDto affiliatedOrganization) throws SQLException;
    boolean updateData(String nameData, String data, int idAffiliatedOrganization) throws SQLException;
    AffiliatedOrganizationDto getAffiliatedOrganization(int idAffilitedOrganization) throws SQLException;
    List<AffiliatedOrganizationDto> getListAffiliatedOrganiztion(String data) throws SQLException;
}
