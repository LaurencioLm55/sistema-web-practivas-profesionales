package com.sistemapracticasprofesional.logic.dto;

public class ProfessorDto {

    private int staffNumber;
    private Integer userId;
    private String name;
    private String shift;

    public ProfessorDto() {
    }

    public ProfessorDto(int staffNumber, Integer userId, String name, 
            String shift) {
        this.staffNumber = staffNumber;
        this.userId = userId;
        this.name = name;
        this.shift = shift;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + staffNumber;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shift == null) ? 0 : shift.hashCode());
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
        ProfessorDto other = (ProfessorDto) obj;
        if (staffNumber != other.staffNumber)
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
        if (shift == null) {
            if (other.shift != null)
                return false;
        } else if (!shift.equals(other.shift))
            return false;
        return true;
    }
    
}
