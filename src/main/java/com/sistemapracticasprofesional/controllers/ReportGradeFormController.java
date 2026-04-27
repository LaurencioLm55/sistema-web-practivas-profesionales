/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistemapracticasprofesional.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Dario Padilla
 */
public class ReportGradeFormController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextArea txtObservationBox;
    @FXML
    private TextField txtGradeField;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnFinish;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickCancel(ActionEvent event) {
    }

    @FXML
    private void onClickFinish(ActionEvent event) {
    }
    
}
