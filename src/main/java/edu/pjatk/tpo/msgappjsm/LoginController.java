package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class LoginController {

    private ChatModel chatModel;

    private ViewModel viewModel;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label optionalLabel;

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void setUserModel(ChatModel chatModel){ this.chatModel = chatModel; }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        if(isValidLogin(nameField.getText(), passwordField.getText())){
            System.out.println("Username: "+nameField.getText());
            chatModel.setUsername(nameField.getText());
            chatModel.receiveMessage();
            viewModel.setCurrentView(ViewModel.View.CHAT);
            nameField.clear();
            passwordField.clear();
        }else{
            optionalLabel.setText("Invalid creditentials");
        }
    }

    @FXML
    private void onSignUpButtonClick(ActionEvent event) throws IOException {
        viewModel.setCurrentView(ViewModel.View.SIGNUP);
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
