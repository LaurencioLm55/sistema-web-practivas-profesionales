package com.sistemapracticasprofesional.controllers;

import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class UserLogginController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void isUserRegister(){
        
        UserDto userDto = new UserDto();
        UserDao userDao = new UserDao();
        getData(userDto);

        try{
            
            boolean success = userDao.isUserRegistred(userDto);

            if(success){

            }else{

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("No se puede iniciar secion");
                alert.setContentText("Usuario o contraseña incorrecta");

            }

        }catch (DaoException e){

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("No se puede iniciar secion");
            alert.setContentText(null);
            
        }
    }

    private void getData( UserDto userDto ){

        userDto.setUserName(userNameTextField.getText());
        userDto.setPassword(passwordField.getText());

    }

}
