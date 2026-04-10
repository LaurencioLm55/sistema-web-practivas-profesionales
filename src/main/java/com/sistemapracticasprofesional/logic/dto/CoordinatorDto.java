package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

public class CoordinatorDto {

    private Integer userId;
    private String name;
    private boolean active; 
    private LocalDate entryDate;
    private LocalDate exitDate;

    public CoordinatorDto() {
    }

    public CoordinatorDto(String name, boolean active, LocalDate entryDate,
            LocalDate exitDate) {
        this.name = name;
        this.active = active;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public CoordinatorDto(Integer userId, String name, boolean active, LocalDate entryDate,
            LocalDate exitDate) {
        this.userId = userId;
        this.name = name;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setState(boolean active) {
        this.active = active;
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

}