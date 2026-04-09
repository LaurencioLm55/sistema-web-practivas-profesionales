package com.sistemapracticasprofesional.logic.dto;

public class PartialReportDto {
    private int proyectActivityId;
    private String plannedTime;
    private String realTime;
    private String results;
    private String observations;

    public PartialReportDto() {
    }

    public PartialReportDto(int proyectActivityId, String plannedTime,
            String realTime, String results, String observations) {
        this.proyectActivityId = proyectActivityId;
        this.plannedTime = plannedTime;
        this.realTime = realTime;
        this.results = results;
        this.observations = observations;
    }

    public PartialReportDto(String plannedTime, String realTime, String results,
            String observations) {
        this.plannedTime = plannedTime;
        this.realTime = realTime;
        this.results = results;
        this.observations = observations;
    }

    public int getProyectActivityId() {
        return proyectActivityId;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public String getResults() {
        return results;
    }

    public String getObservations() {
        return observations;
    }

    public void setProyectActivityId(int proyectActivityId) {
        this.proyectActivityId = proyectActivityId;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
