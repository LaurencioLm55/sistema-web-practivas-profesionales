package edu.uv.sistemapracticasprofesionales;

import edu.uv.sistemapracticasprofesionales.logic.dao.MonthlyReportDao;
import edu.uv.sistemapracticasprofesionales.logic.dto.MonthlyReportDto;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class SistemaPracticasProfesionales {

    public static void main(String[] args) {
       
        
        MonthlyReportDao monthly = new MonthlyReportDao();
        
        
 
        
        try{
            
            List<MonthlyReportDto> list = monthly.getListMonthlyReport("s23014080");
            
            for(int i = 0; i <= 3; i++){
                System.out.println(list.get(i).getDescription());
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
