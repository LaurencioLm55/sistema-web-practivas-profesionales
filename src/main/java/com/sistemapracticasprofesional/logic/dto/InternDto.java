package com.sistemapracticasprofesional.logic.dto;

public class InternDto {
    private String matricula;
    private int edad;
    private String nombre;
    private String lenguaIndigena;
    private String genero;
    private String carrera;

    public InternDto(String matricula, int edad, String nombre, String lenguaIndigena, 
                     String genero, String carrera) {
        this.matricula = matricula;
        this.edad = edad;
        this.nombre = nombre;
        this.lenguaIndigena = lenguaIndigena;
        this.genero = genero;
        this.carrera = carrera;
    }

   

    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLenguaIndigena() {
        return lenguaIndigena;
    }

    public void setLenguaIndigena(String lenguaIndigena) {
        this.lenguaIndigena = lenguaIndigena;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
     
}
