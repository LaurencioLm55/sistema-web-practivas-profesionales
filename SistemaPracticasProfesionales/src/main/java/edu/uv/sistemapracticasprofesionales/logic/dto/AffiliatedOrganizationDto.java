package edu.uv.sistemapracticasprofesionales.logic.dto;

public class AffiliatedOrganizationDto {
    private int idOrganization;
    private String name;
    private String address;
    private String sector;
    private String city;
    private String state;
    private String phoneNumber;
    private String email;
    
    public AffiliatedOrganizationDto(){
        
    }
    
    public AffiliatedOrganizationDto(int idOrganization, String name, String address, String sector, String city, String state, String phoneNumber, String email){
       this.idOrganization = idOrganization;
       this.name = name;
       this.address = address;
       this.sector = sector;
       this.city = city;
       this.state = state;
       this.phoneNumber = phoneNumber;
       this.email = email;
    }
    
    public AffiliatedOrganizationDto(String name, String address, String sector, String city, String state, String phoneNumber, String email){
       this.name = name;
       this.address = address;
       this.sector = sector;
       this.city = city;
       this.state = state;
       this.phoneNumber = phoneNumber;
       this.email = email;
    }
    
    public int getIdOrganization(){
        return this.idOrganization;
    }
    
    public void setOrganization(int idOrganization){
        this.idOrganization = idOrganization;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getSector(){
        return this.sector;
    }
    
    public void setSector(String sector){
        this.sector = sector;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getPhoneNumeber(){
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
}
