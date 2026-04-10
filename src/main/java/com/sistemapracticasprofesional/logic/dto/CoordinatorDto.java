/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

public class CoordinatorDto {
    private Integer userId;
    private String name;
    private boolean active;
    private LocalDate entryDate;
    private LocalDate exitDate;

    public CoordinatorDto() 
    {
    }

    public CoordinatorDto(String name, boolean active, LocalDate entryDate, LocalDate exitDate) {
        this.name = name;
        this.active = active;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }
    
    public CoordinatorDto(Integer userId, String name, boolean active,
            LocalDate entryDate, LocalDate exitDate) 
    {
        this.userId = userId;
        this.name = name;
        this.active = active;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public String getName()
    {
        return name;
    }
    public boolean isActive()
    {
        return active;
    }
    public LocalDate getEntryDate()
    {
        return entryDate;
    }
    public LocalDate getExitDate()
    {
        return exitDate;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setState(boolean active)
    {
        this.active = active;
    }
    public void setEntryDate(LocalDate entryDate)
    {
        this.entryDate = entryDate;
    }
    public void setExitDate(LocalDate exitDate)
    {
        this.exitDate = exitDate;
    }
    
    
    
}
