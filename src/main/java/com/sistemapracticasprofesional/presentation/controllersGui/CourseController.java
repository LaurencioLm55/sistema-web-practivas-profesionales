package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.service.CourseService;
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

   private final CourseService courseService = new CourseService();

   @FXML
   private void handleRegisterCourse() {
      try {
         courseService.registerCourse(
                  textFieldNrc.getText(),
                  textFieldStaffNumber.getText(),
                  textFieldStatus.getText(),
                  textFieldPeriod.getText(),
                  textFieldSection.getText()
         );

         showAlert(Alert.AlertType.INFORMATION, "Curso registrado correctamente.");
         clearFields();

      } catch (ValidationException e) {
         showAlert(Alert.AlertType.WARNING, e.getMessage());

      } catch (ServiceException e) {
         showAlert(Alert.AlertType.ERROR, "No se pudo completar la operación.");
      }
   }

   @FXML
   private void handleCancel() {
      clearFields();
   }

   private CourseDto getCourseFromFields() {
      String nrcText = textFieldNrc.getText().trim();
      String staffNumberText = textFieldStaffNumber.getText().trim();
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