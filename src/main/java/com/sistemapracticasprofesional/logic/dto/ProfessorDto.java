package com.sistemapracticasprofesional.logic.dto;

public class ProfessorDto {

    private int staffNumber;
    private Integer userId;
    private String name;
    private String shift;
    private boolean active;

    public ProfessorDto() {
    }

    public ProfessorDto(int staffNumber, Integer userId, String name, 
            String shift, boolean active) {
        this.staffNumber = staffNumber;
        this.userId = userId;
        this.name = name;
        this.shift = shift;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
