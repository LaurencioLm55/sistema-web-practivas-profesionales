package com.sistemapracticasprofesional.logic.dto;

public class ProfessorDto {
    private int professorUserId;
    private int professorPersonnelNumber;
    private String professorName;
    private String professorShift;
    private String professorCourseId;

    public ProfessorDto() {
    }

    public ProfessorDto(int professorPersonnelNumber, String professorName, String professorShift, String professorCourseId) {
        this.professorPersonnelNumber = professorPersonnelNumber;
        this.professorName = professorName;
        this.professorShift = professorShift;
        this.professorCourseId = professorCourseId;
    }

    public ProfessorDto(int professorPersonnelNumber, int professorUserId, String professorName, String professorShift, String professorCourseId) {
        this.professorPersonnelNumber = professorPersonnelNumber;
        this.professorUserId = professorUserId;
        this.professorName = professorName;
        this.professorShift = professorShift;
        this.professorCourseId = professorCourseId;
    }

    public int getProfessorUserId() {
        return professorUserId;
    }

    public int getProfessorPersonnelNumber() {
        return professorPersonnelNumber;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getProfessorShift() {
        return professorShift;
    }

    public String getProfessorCourseId() {
        return professorCourseId;
    }

    public void setProfessorUserId(int professorUserId) {
        this.professorUserId = professorUserId;
    }

    public void setProfessorPersonnelNumber(int professorPersonnelNumber) {
        this.professorPersonnelNumber = professorPersonnelNumber;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public void setProfessorShift(String professorShift) {
        this.professorShift = professorShift;
    }

    public void setProfessorCourseId(String professorCourseId) {
        this.professorCourseId = professorCourseId;
    }
}
