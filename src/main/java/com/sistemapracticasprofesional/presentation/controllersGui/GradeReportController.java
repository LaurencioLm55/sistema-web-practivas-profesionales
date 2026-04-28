package com.sistemapracticasprofesional.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
