package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.service.InternService;
import com.sistemapracticasprofesional.presentation.validation.FormValidator;
import com.sistemapracticasprofesional.presentation.validation.ValidationResult;
import java.util.LinkedHashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class InternController {

   @FXML
   private TextField textFieldStudentId;

   @FXML
   private TextField textFieldAge;

   @FXML
   private TextField textFieldName;

   @FXML
   private TextField textFieldIndigenousLanguage;

   @FXML
   private TextField textFieldGender;

   @FXML
   private TextField textFieldMajor;

   private final InternService internService = new InternService();

   @FXML
   private void initialize() {
      FormValidator.allowOnlyPositiveIntegers(textFieldAge);
   }

   @FXML
   private void handleRegisterIntern() {
      ValidationResult validationResult = validateForm();

      if (!validationResult.isValid()) {
         showAlert(Alert.AlertType.WARNING, validationResult.getMessage());
         return;
      }

      try {
         internService.registerIntern(
                  textFieldStudentId.getText(),
                  textFieldAge.getText(),
                  textFieldName.getText(),
                  textFieldIndigenousLanguage.getText(),
                  textFieldGender.getText(),
                  textFieldMajor.getText()
         );

         showAlert(Alert.AlertType.INFORMATION, "Intern registered successfully.");
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
      textFieldStudentId.clear();
      textFieldAge.clear();
      textFieldName.clear();
      textFieldIndigenousLanguage.clear();
      textFieldGender.clear();
      textFieldMajor.clear();
   }

   private ValidationResult validateForm() {
      LinkedHashMap<TextField, String> requiredFields = new LinkedHashMap<>();
      requiredFields.put(textFieldStudentId, "Student ID");
      requiredFields.put(textFieldAge, "Age");
      requiredFields.put(textFieldName, "Name");
      requiredFields.put(textFieldGender, "Gender");
      requiredFields.put(textFieldMajor, "Major");

      ValidationResult requiredValidation = FormValidator.validateRequiredFields(requiredFields);

      if (!requiredValidation.isValid()) {
         return requiredValidation;
      }

      return FormValidator.validatePositiveInteger(textFieldAge, "Age");
   }

   private void clearValidationStyles() {
      FormValidator.clearStyles(
               textFieldStudentId,
               textFieldAge,
               textFieldName,
               textFieldIndigenousLanguage,
               textFieldGender,
               textFieldMajor
      );
   }

   private void showAlert(Alert.AlertType type, String message) {
      Alert alert = new Alert(type);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
