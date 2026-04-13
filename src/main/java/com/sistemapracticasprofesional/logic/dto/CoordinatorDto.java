package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

public class CoordinatorDto {

    private int personnelNumber;
    private int userId;
    private String name;
    private String state;
    private LocalDate entryDate;
    private LocalDate exitDate;

    public CoordinatorDto() {
    }

    public CoordinatorDto(int personnelNumber, String name, String state, LocalDate entryDate,
            LocalDate exitDate) {
        this.personnelNumber = personnelNumber;
        this.name = name;
        this.state = state;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public CoordinatorDto(int personnelNumber, int userId, String name, String state, LocalDate entryDate,
            LocalDate exitDate) {
        this.personnelNumber = personnelNumber;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }
}
