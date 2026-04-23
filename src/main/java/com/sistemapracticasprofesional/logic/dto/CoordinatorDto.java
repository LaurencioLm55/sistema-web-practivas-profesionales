package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

public class CoordinatorDto {

    private int personnelNumber;
    private Integer userId;
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

    public CoordinatorDto(int personnelNumber, Integer userId, String name, String state, LocalDate entryDate,
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + personnelNumber;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
        result = prime * result + ((exitDate == null) ? 0 : exitDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CoordinatorDto other = (CoordinatorDto) obj;
        if (personnelNumber != other.personnelNumber)
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (entryDate == null) {
            if (other.entryDate != null)
                return false;
        } else if (!entryDate.equals(other.entryDate))
            return false;
        if (exitDate == null) {
            if (other.exitDate != null)
                return false;
        } else if (!exitDate.equals(other.exitDate))
            return false;
        return true;
    }

    
}
