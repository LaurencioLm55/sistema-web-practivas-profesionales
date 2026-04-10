package com.sistemapracticasprofesional.logic.dto;
import java.time.LocalDate;

public class MonthlyReportDto {
    private int monthlyReportId;
    private String internId;
    private float score;
    private String monthlyReportFile;
    private LocalDate dateOfCompletion;
    private LocalDate deliveryDate;
    private String description;
    
    public MonthlyReportDto(){}
    
    public MonthlyReportDto(int monthlyReportId, String internId, float score,
            String monthlyReportFile, LocalDate dateOfCompletion, 
            LocalDate deliveryDate, String description){
        
        this.monthlyReportId = monthlyReportId;
        this.internId = internId;
        this.score = score;
        this.monthlyReportFile = monthlyReportFile;
        this.dateOfCompletion = dateOfCompletion;
        this.deliveryDate = deliveryDate;
        this.description = description;
        
    }
    
    public MonthlyReportDto(String internId, float score, String monthlyReportFile,
            LocalDate dateOfCompletion, LocalDate deliveryDate, String description){
        
        this.internId = internId;
        this.score = score;
        this.monthlyReportFile = monthlyReportFile;
        this.dateOfCompletion = dateOfCompletion;
        this.deliveryDate = deliveryDate;
        this.description = description;
        
    }

    public int getMonthlyReportId() {
        return monthlyReportId;
    }

    public void setMonthlyReportId(int monthlyReportId) {
        this.monthlyReportId = monthlyReportId;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getMonthlyReportFile() {
        return monthlyReportFile;
    }

    public void setMonthlyReportFile(String monthlyReportFile) {
        this.monthlyReportFile = monthlyReportFile;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
