/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

/**
 *
 * @author Dario Padilla
 */
public class CoordinatorDTO {
    private Integer userId;
    private String name;
    private String state;
    private LocalDate entryDate;
    private LocalDate exitDate;

    public CoordinatorDTO() 
    {
    }
    
    public CoordinatorDTO(Integer userId, String name, String state,
            LocalDate entryDate, LocalDate exitDate) 
    {
        this.userId = userId;
        this.name = name;
        this.state = state;
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
    public String getState()
    {
        return state;
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
    public void setState(String state)
    {
        this.state = state;
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
