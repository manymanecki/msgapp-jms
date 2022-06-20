package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ChatController {

    private ViewModel viewModel;

    @FXML
    private Text usernameText;

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//
//        // todo
//        // Troche brudna zmiana wielkosci okna poniewaz po wylogowaniu
//        // ekran logowania byl za duzy. Sprawdzic pozniej czy jest lepszy
//        // sposob na zmiane tego.
//        stage.setMinHeight(0);
//        stage.setMinWidth(0);
//        stage.sizeToScene();
//        stage.setResizable(false);
//
//        scene.getWindow().centerOnScreen();
//        stage.show();
        viewModel.setCurrentView(ViewModel.View.LOGIN);
    }

    public void setUsernameText(String username){
        usernameText.setText(username);
    }
}