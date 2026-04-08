package com.sistemapracticasprofesional.logic.dto;

public class ProjectDto {
    private int projectId;
    private int linkedOrganizationId;
    private String projectName;
    private String projectDescription;
    private String projectMethodology;
    private String projectResources;
    private String midtermProjectObjectives;
    private String generalProjectObjective;
    private String inmediateProjectObjectives;
    private String projectResponsabilities;
    private String projectAttendantName;
    private String projectAttendantEmail;
    private String projectAttendantSchedule;

    public ProjectDto() {
    }

    public ProjectDto(int projectId, int linkedOrganizationId, String projectName, String projectDescription, String projectMethodology, String projectResources, String midtermProjectObjectives, String generalProjectObjective, String inmediateProjectObjectives, String projectResponsabilities, String projectAttendantName, String projectAttendantEmail, String projectAttendantSchedule) {
        this.projectId = projectId;
        this.linkedOrganizationId = linkedOrganizationId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMethodology = projectMethodology;
        this.projectResources = projectResources;
        this.midtermProjectObjectives = midtermProjectObjectives;
        this.generalProjectObjective = generalProjectObjective;
        this.inmediateProjectObjectives = inmediateProjectObjectives;
        this.projectResponsabilities = projectResponsabilities;
        this.projectAttendantName = projectAttendantName;
        this.projectAttendantEmail = projectAttendantEmail;
        this.projectAttendantSchedule = projectAttendantSchedule;
    }

    public ProjectDto(int linkedOrganizationId, String projectName, String projectDescription, String projectMethodology, String projectResources, String midtermProjectObjectives, String generalProjectObjective, String inmediateProjectObjectives, String projectResponsabilities, String projectAttendantName, String projectAttendantEmail, String projectAttendantSchedule) {
        this.linkedOrganizationId = linkedOrganizationId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMethodology = projectMethodology;
        this.projectResources = projectResources;
        this.midtermProjectObjectives = midtermProjectObjectives;
        this.generalProjectObjective = generalProjectObjective;
        this.inmediateProjectObjectives = inmediateProjectObjectives;
        this.projectResponsabilities = projectResponsabilities;
        this.projectAttendantName = projectAttendantName;
        this.projectAttendantEmail = projectAttendantEmail;
        this.projectAttendantSchedule = projectAttendantSchedule;
    }   
    
    public int getProjectId() {
        return projectId;
    }

    public int getLinkedOrganizationId() {
        return linkedOrganizationId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectMethodology() {
        return projectMethodology;
    }

    public String getProjectResources() {
        return projectResources;
    }

    public String getMidtermProjectObjectives() {
        return midtermProjectObjectives;
    }

    public String getGeneralProjectObjective() {
        return generalProjectObjective;
    }

    public String getInmediateProjectObjectives() {
        return inmediateProjectObjectives;
    }

    public String getProjectResponsabilities() {
        return projectResponsabilities;
    }

    public String getProjectAttendantName() {
        return projectAttendantName;
    }

    public String getProjectAttendantEmail() {
        return projectAttendantEmail;
    }

    public String getProjectAttendantSchedule() {
        return projectAttendantSchedule;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setLinkedOrganizationId(int linkedOrganizationId) {
        this.linkedOrganizationId = linkedOrganizationId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectMethodology(String projectMethodology) {
        this.projectMethodology = projectMethodology;
    }

    public void setProjectResources(String projectResources) {
        this.projectResources = projectResources;
    }

    public void setMidtermProjectObjectives(String midtermProjectObjectives) {
        this.midtermProjectObjectives = midtermProjectObjectives;
    }

    public void setGeneralProjectObjective(String generalProjectObjective) {
        this.generalProjectObjective = generalProjectObjective;
    }

    public void setInmediateProjectObjectives(String inmediateProjectObjectives) {
        this.inmediateProjectObjectives = inmediateProjectObjectives;
    }

    public void setProjectResponsabilities(String projectResponsabilities) {
        this.projectResponsabilities = projectResponsabilities;
    }

    public void setProjectAttendantName(String projectAttendantName) {
        this.projectAttendantName = projectAttendantName;
    }

    public void setProjectAttendantEmail(String projectAttendantEmail) {
        this.projectAttendantEmail = projectAttendantEmail;
    }

    public void setProjectAttendantSchedule(String projectAttendantSchedule) {
        this.projectAttendantSchedule = projectAttendantSchedule;
    }
    
    
}   
