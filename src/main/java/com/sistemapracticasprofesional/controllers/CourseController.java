package com.sistemapracticasprofesional.controllers;

import com.sistemapracticasprofesional.logic.dao.CourseDao;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class CourseController {
   @FXML
   private TextField textFieldNrc;
   
   @FXML
   private TextField textFieldStaffNumber;
   
   @FXML
   private TextField textFieldStatus;
   
   @FXML
   private TextField textFieldPeriod;
   
   @FXML
   private TextField textFieldSection;

   private final CourseDao courseDao = new CourseDao();

   @FXML
   private void handleRegisterCourse(){
      try{
         CourseDao course = getCourseFromFields();
         boolean registered = courseDao.registerCourse(course);

         if (registered) {
            showAlert(Alert.AlertType.INFORMATION, "Curso registrado correctamente");
            clearFields();
            } else {
            showAlert(Alert.AlertType.ERROR, "No se pudo registrar el curso");
            }
            
         } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.WARNING, "Los datos capturados no son validos.");
         } catch (DaoException e) {
            showAlert(Alert.AlertType.ERROR, "No se pudo completar la operacion.");
         }

         
   private CourseDto getCourseFromFields() {
      String nrcText = textFieldNrc.getText().trim();
      String staffNumberText = textFieldStaffNumber.getText().trim();
      String status = textFieldStatus.getText().trim();
      String period = textFieldPeriod.getText().trim();
      String section = textFieldSection.getText().trim();

      if (nrcText.isEmpty() || staffNumberText.isEmpty() || status.isEmpty()
         || period.isEmpty() || section.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
      }

      if (!nrcText.matches("\\d+")) {
         throw new IllegalArgumentException("El NRC debe ser numerico");
      }

      if (!staffNumberText.matches("\\d+")) {
         throw new IllegalArgumentException("El numero de personal debe ser numerico");
      }

      CourseDto course = new CourseDto();
      course.setNrc(Integer.parseInt(nrcText));
      course.setStaffNumber(Integer.parseInt(staffNumberText));
      course.setStatus(status);
      course.setPeriod(period);
      course.setSection(section);
      course.setFormatFile(null);

      return course;
   }

   private void clearFields() {
      textFieldNrc.clear();
      textFieldStaffNumber.clear();
      textFieldStatus.clear();
      textFieldPeriod.clear();
      textFieldSection.clear();
   }

   private void showAlert(Alert.AlertType type, String message) {
      Alert alert = new Alert(type);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
