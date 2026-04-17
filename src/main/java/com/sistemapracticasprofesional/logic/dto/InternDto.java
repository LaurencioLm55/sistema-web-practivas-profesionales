package com.sistemapracticasprofesional.logic.dto;

public class InternDto {
    private String studentId;
    private int age;
    private String name;
    private String indigenousLanguage;
    private String gender;
    private String major;

    public InternDto(String studentId, int age, String name, String indigenousLanguage,
                     String gender, String major) {
        this.studentId = studentId;
        this.age = age;
        this.name = name;
        this.indigenousLanguage = indigenousLanguage;
        this.gender = gender;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndigenousLanguage() {
        return indigenousLanguage;
    }

    public void setIndigenousLanguage(String indigenousLanguage) {
        this.indigenousLanguage = indigenousLanguage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
     
}
