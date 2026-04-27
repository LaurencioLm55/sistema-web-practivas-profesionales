package com.sistemapracticasprofesional.presentation.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public final class FormValidator {

    private static final String INVALID_FIELD_STYLE = "-fx-border-color: #c0392b; -fx-border-width: 1.2;";

    private FormValidator() {
    }

    public static void allowOnlyPositiveIntegers(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.matches("\\d*")) {
                return change;
            }

            return null;
        }));
    }

    public static ValidationResult validateRequiredFields(LinkedHashMap<TextField, String> fields) {
        for (Map.Entry<TextField, String> field : fields.entrySet()) {
            TextField textField = field.getKey();

            if (isBlank(textField.getText())) {
                markInvalid(textField);
                textField.requestFocus();

                return ValidationResult.invalid(field.getValue() + " is required.");
            }

            clearStyle(textField);
        }

        return ValidationResult.valid();
    }

    public static ValidationResult validatePositiveInteger(TextField textField, String fieldName) {
        if (isBlank(textField.getText())) {
            markInvalid(textField);
            textField.requestFocus();

            return ValidationResult.invalid(fieldName + " is required.");
        }

        try {
            int value = Integer.parseInt(textField.getText().trim());

            if (value <= 0) {
                markInvalid(textField);
                textField.requestFocus();

                return ValidationResult.invalid(fieldName + " must be greater than zero.");
            }

            clearStyle(textField);
            return ValidationResult.valid();

        } catch (NumberFormatException e) {
            markInvalid(textField);
            textField.requestFocus();

            return ValidationResult.invalid(fieldName + " must be numeric.");
        }
    }

    public static void clearStyles(TextField... fields) {
        for (TextField field : fields) {
            clearStyle(field);
        }
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    private static void markInvalid(TextField textField) {
        textField.setStyle(INVALID_FIELD_STYLE);
    }

    private static void clearStyle(TextField textField) {
        textField.setStyle("");
    }
}
