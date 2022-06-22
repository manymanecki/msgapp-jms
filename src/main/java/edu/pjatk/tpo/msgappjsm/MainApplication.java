package edu.pjatk.tpo.msgappjsm;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewModel viewModel = new ViewModel();
        ChatModel chatModel = new ChatModel()
;
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent login = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        loginController.setViewModel(viewModel);
        loginController.setUserModel(chatModel);

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent chat = chatLoader.load();
        ChatController chatController = chatLoader.getController();
        chatController.setViewModel(viewModel);
        chatController.setUserModel(chatModel);

        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent signUp = signUpLoader.load();
        SignUpController signUpController = signUpLoader.getController();
        signUpController.setViewModel(viewModel);

        Scene scene = new Scene(login);
        scene.rootProperty().bind(Bindings.createObjectBinding(() -> {
            if (viewModel.getCurrentView() == ViewModel.View.LOGIN) {
                System.out.println("ViewModel.View.LOGIN");
                stage.setTitle("msgapp-jsm - Login");
                initializeScreen(stage, false, 300, 430);
                return login;
            } else if (viewModel.getCurrentView() == ViewModel.View.CHAT) {
                System.out.println("ViewModel.View.CHAT");
                stage.setTitle("msgapp-jsm - Chat");
                initializeScreen(stage, true, 800, 600);
                chatController.setUsernameText();
                return chat;
            } else if (viewModel.getCurrentView() == ViewModel.View.SIGNUP) {
                System.out.println("ViewModel.View.SIGNUP");
                stage.setTitle("msgapp-jsm - Sign-up");
                initializeScreen(stage, false, 300, 430);
                return signUp;
            } else {
                return null;
            }
        }, viewModel.currentViewProperty()));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void initializeScreen(Stage stage, boolean resizable, double width, double height){
        stage.setResizable(resizable);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.centerOnScreen();
    }
}