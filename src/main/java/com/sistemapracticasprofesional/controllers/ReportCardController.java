package com.sistemapracticasprofesional.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
