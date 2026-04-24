package com.sistemapracticasprofesional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaPracticasProfesional extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/sistemapracticasprofesional/presentation/GuiCourses.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Registro Experiencia Educativa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
