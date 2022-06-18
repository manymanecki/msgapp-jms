package edu.pjatk.tpo.msgappjsm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        scene.getStylesheets().setAll(
                Objects.requireNonNull(getClass().getResource("login.css")).toExternalForm()
        );

        stage.setTitle("msgapp-jsm - Login screen");
        stage.setScene(scene);
        stage.sizeToScene();

        // Transparent background to make rounded corners work
        scene.setFill(Color.TRANSPARENT);
        //stage.initStyle(StageStyle.TRANSPARENT);

        // Lock login screen to set dimensions
        stage.setMinWidth(300);
        stage.setMinHeight(400);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}