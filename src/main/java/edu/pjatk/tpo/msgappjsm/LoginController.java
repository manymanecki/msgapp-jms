package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button forgotPasswordButton;

    @FXML
    private Label optionalLabel;

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        if(isValidLogin(nameField.getText(), passwordField.getText())){
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("chat.fxml"));
            Parent root = loader.load();
            ChatController chatController = loader.getController();
            chatController.setUsernameText(nameField.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            scene.getWindow().centerOnScreen();
            stage.show();
        }else{
            optionalLabel.setText("Invalid creditentials");
        }
    }

    @FXML
    private void onSignUpButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("signup.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getWindow().centerOnScreen();
        stage.show();
    }

    private boolean isValidLogin(String username, String password){
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/edu/pjatk/tpo/msgappjsm/textfiles/creditentials"));
            for (String line : lines) {
                String[] splitLine = line.split("\\s+");
                if (Objects.equals(splitLine[1], username)) {
                    if(Objects.equals(splitLine[2], password)){
                        return true;
                    }
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
