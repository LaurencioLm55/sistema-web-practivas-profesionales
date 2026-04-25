/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistemapracticasprofesional.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dario Padilla
 */
public class GradeReportController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabMonthlies;
    @FXML
    private Tab tabPartials;
    @FXML
    private VBox vBoxPartials;
    @FXML
    private VBox vBoxMonthlies;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
