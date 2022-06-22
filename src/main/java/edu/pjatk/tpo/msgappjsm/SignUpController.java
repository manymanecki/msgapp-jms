package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class SignUpController {

    private ViewModel viewModel;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Text errorText;

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    @FXML
    private void onReturnButtonClick(ActionEvent event) {
        viewModel.setCurrentView(ViewModel.View.LOGIN);
        errorText.setText("");
    }

    @FXML
    private void onSignUpButtonClick(ActionEvent event) throws IOException {
        if (isValidSignUp()) {
            String registerForm = String.join(" ", emailField.getText(), nameField.getText(), passwordField.getText()+" \n");
            System.out.println(registerForm);
            Files.writeString(Path.of("src/main/resources/edu/pjatk/tpo/msgappjsm/textfiles/creditentials"), registerForm, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            onReturnButtonClick(event);
        }
    }

    // todo
    // Ulepszyc walidacje o pewne edge case'y takie jak:
    // - brak pola uzytkownika
    // - email juz istnieje w bazie
    // -

    private boolean isValidSignUp(){
        String errorMessage = "";
        boolean result = true;
        if (!(isValidEmailAddress(emailField.getText()) || Objects.equals(emailField.getText(), "admin@admin.pl"))){
            result = false;
            errorMessage=errorMessage+"Invalid email address\n";
        }
        if (!(isValidPassword(passwordField.getText())) || Objects.equals(passwordField.getText(), "admin")){
            result = false;
            errorMessage=errorMessage+"Password is too short\n";
        }else{
            if(!Objects.equals(passwordField.getText(), repeatPasswordField.getText())){
                result = false;
                errorMessage=errorMessage+"Passwords don't match\n";
            }
        }
        if (!(isValidUsername(nameField.getText())) || Objects.equals(nameField.getText(), "admin")){
            result = false;
            errorMessage=errorMessage+"Username with that name exists\n";
        }
        errorText.setText(errorMessage);
        return result;
    }

    private static boolean isValidEmailAddress(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    private static boolean isValidPassword(String password){
        return password.length() >= 6;
    }

    private static boolean isValidUsername(String username) {
        if (username.contains(" ") || username.length()<=3){
            return false;
        }
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/edu/pjatk/tpo/msgappjsm/textfiles/creditentials"));
            for (String line : lines) {
                String[] splitLine = line.split("\\s+");
                if (Objects.equals(splitLine[1], username)) {
                    return false;
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return true;
    }
}
