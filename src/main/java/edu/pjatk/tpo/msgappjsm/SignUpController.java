package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button forgotPasswordButton;

    @FXML
    private Button returnButton;

    @FXML
    private void onReturnButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getWindow().centerOnScreen();
        stage.show();
    }

    private void onSignUpButtonClick(ActionEvent event) throws IOException {
        if (isValidSignUp()){
            String registerForm = String.join(" ", emailField.toString(), nameField.toString(), passwordField.toString());
            Files.writeString(Path.of("creditentials"), registerForm, StandardCharsets.UTF_8);
        }
    }

    private boolean isValidSignUp(){
        boolean result = true;
        if (!(isValidEmailAdress(emailField.toString()) || emailField.toString()=="admin@admin.pl")){
            result = false;
            //todo - poinformowac ze email jest zly
        }
        if (!(isValidPassword(passwordField.toString())) || passwordField.toString()=="admin"){
            result = false;
            //todo - poinformowac ze haslo jest za krotkie (minimum 6 znakow)
        }else{
            if(!Objects.equals(passwordField.toString(), repeatPasswordField.toString())){
                result = false;
                //todo - poinformowac ze hasla nie sa takie same
            }
        }
        if (!(isValidUsername(nameField.toString())) || nameField.toString()=="admin"){
            result = false;
            //todo - poinformowac ze taka nazwa uzytkownika juz istnieje
        }
        return result;
    }

    private static boolean isValidEmailAdress(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    private static boolean isValidPassword(String password){
        return password.length() <= 6;
    }

    private static boolean isValidUsername(String username){
        //todo - wyszukiwarka nazwy uzytkownika w pliku creditentials
        return false;
    }
}
