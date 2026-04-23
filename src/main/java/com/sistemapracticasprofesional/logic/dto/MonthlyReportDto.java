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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + monthlyReportId;
        result = prime * result + ((internId == null) ? 0 : internId.hashCode());
        result = prime * result + Float.floatToIntBits(score);
        result = prime * result + ((monthlyReportFile == null) ? 0 : monthlyReportFile.hashCode());
        result = prime * result + ((dateOfCompletion == null) ? 0 : dateOfCompletion.hashCode());
        result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        MonthlyReportDto other = (MonthlyReportDto) obj;
        if (monthlyReportId != other.monthlyReportId)
            return false;
        if (internId == null) {
            if (other.internId != null)
                return false;
        } else if (!internId.equals(other.internId))
            return false;
        if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
            return false;
        if (monthlyReportFile == null) {
            if (other.monthlyReportFile != null)
                return false;
        } else if (!monthlyReportFile.equals(other.monthlyReportFile))
            return false;
        if (dateOfCompletion == null) {
            if (other.dateOfCompletion != null)
                return false;
        } else if (!dateOfCompletion.equals(other.dateOfCompletion))
            return false;
        if (deliveryDate == null) {
            if (other.deliveryDate != null)
                return false;
        } else if (!deliveryDate.equals(other.deliveryDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }
    
    
}
