package com.sistemapracticasprofesional.controllers;

import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.service.CourseService;
import com.sistemapracticasprofesional.presentation.validation.FormValidator;
import com.sistemapracticasprofesional.presentation.validation.ValidationResult;
import java.util.LinkedHashMap;
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
   private void initialize() {
      FormValidator.allowOnlyPositiveIntegers(textFieldNrc);
      FormValidator.allowOnlyPositiveIntegers(textFieldStaffNumber);
   }

   @FXML
   private void handleRegisterCourse() {
      ValidationResult validationResult = validateForm();

      if (!validationResult.isValid()) {
         showAlert(Alert.AlertType.WARNING, validationResult.getMessage());
         return;
      }

      try {
         courseService.registerCourse(
                  textFieldNrc.getText(),
                  textFieldStaffNumber.getText(),
                  textFieldStatus.getText(),
                  textFieldPeriod.getText(),
                  textFieldSection.getText()
         );

         showAlert(Alert.AlertType.INFORMATION, "Course registered successfully.");
         clearFields();

      } catch (ValidationException e) {
         showAlert(Alert.AlertType.WARNING, e.getMessage());

      } catch (ServiceException e) {
         showAlert(Alert.AlertType.ERROR, "The operation could not be completed.");
      }
   }

   @FXML
   private void handleCancel() {
      clearFields();
      clearValidationStyles();
   }

   private void clearFields() {
      textFieldNrc.clear();
      textFieldStaffNumber.clear();
      textFieldStatus.clear();
      textFieldPeriod.clear();
      textFieldSection.clear();
   }

   private ValidationResult validateForm() {
      LinkedHashMap<TextField, String> requiredFields = new LinkedHashMap<>();
      requiredFields.put(textFieldNrc, "NRC");
      requiredFields.put(textFieldStaffNumber, "Staff number");
      requiredFields.put(textFieldStatus, "Status");
      requiredFields.put(textFieldPeriod, "Period");
      requiredFields.put(textFieldSection, "Section");

      ValidationResult requiredValidation = FormValidator.validateRequiredFields(requiredFields);

      if (!requiredValidation.isValid()) {
         return requiredValidation;
      }

      ValidationResult nrcValidation = FormValidator.validatePositiveInteger(textFieldNrc, "NRC");

      if (!nrcValidation.isValid()) {
         return nrcValidation;
      }

      return FormValidator.validatePositiveInteger(textFieldStaffNumber, "Staff number");
   }

   private void clearValidationStyles() {
      FormValidator.clearStyles(
               textFieldNrc,
               textFieldStaffNumber,
               textFieldStatus,
               textFieldPeriod,
               textFieldSection
      );
   }

   private void showAlert(Alert.AlertType type, String message) {
      Alert alert = new Alert(type);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
