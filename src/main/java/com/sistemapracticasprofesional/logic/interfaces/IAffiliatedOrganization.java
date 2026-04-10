package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import java.sql.SQLException;
import java.util.List;
public interface IAffiliatedOrganization {
    
    boolean registredOrganization(AffiliatedOrganizationDto affiliatedOrganization);
    boolean updateAffiliatedOrganization(AffiliatedOrganizationDto affiliatedOrganization, int idAffiliatedOrganization);
    AffiliatedOrganizationDto getAffiliatedOrganization(int idAffilitedOrganization);
    List<AffiliatedOrganizationDto> getListAffiliatedOrganiztionActiveState(String data);
    
}
