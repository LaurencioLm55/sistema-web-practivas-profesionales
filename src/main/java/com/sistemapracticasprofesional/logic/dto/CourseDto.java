
package com.sistemapracticasprofesional.logic.dto;

/**
 *
 * @author lopez
 */
public class CourseDto {
    private int nrc;
    private int staffNumber;
    private String status;
    private String period;
    private String section;
    private String formatFile;
    
    public CourseDto(){
    }

    public CourseDto(int nrc, int staffNumber, String status, String period, String section, String formatFile) {
        this.nrc = nrc;
        this.staffNumber = staffNumber;
        this.status = status;
        this.period = period;
        this.section = section;
        this.formatFile = formatFile;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }
    
    
    
}
        
   