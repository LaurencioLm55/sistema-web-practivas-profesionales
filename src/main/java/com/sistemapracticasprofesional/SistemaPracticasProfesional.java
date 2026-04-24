package com.sistemapracticasprofesional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaPracticasProfesional extends Application{
    public static void main(String[] agrs){
        launch(agrs);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemapracticasprofesional/ui/userloggin/user_loggin.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Mi Aplicación");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
