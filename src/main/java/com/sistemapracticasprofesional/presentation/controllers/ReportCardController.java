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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Dario Padilla
 */
public class ReportCardController implements Initializable {

    @FXML
    private Label labelNombreAlumno;
    @FXML
    private Label labelMatricula;
    @FXML
    private Label labelSeccion;
    @FXML
    private Button btnGrade;
    @FXML
    private Label lblTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickGrade(ActionEvent event) {
    }
    
}
