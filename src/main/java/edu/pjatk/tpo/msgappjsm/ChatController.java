package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChatController {
    @FXML
    private Button logoutButton;

    @FXML
    private Text usernameText;

    @FXML
    private void onLogoutButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getWindow().centerOnScreen();
        stage.show();
    }

    public void setUsernameText(String username){
        usernameText.setText(username);
    }
}