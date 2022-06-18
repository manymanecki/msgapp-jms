package edu.pjatk.tpo.msgappjsm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().setAll(
                getClass().getResource("chat.css").toExternalForm()
        );

        stage.setTitle("msgapp-jsm - Instant Messaging Application");
        stage.setScene(scene);
        stage.sizeToScene();

        // Transparent background to make rounded corners work
        scene.setFill(Color.TRANSPARENT);
        //stage.initStyle(StageStyle.TRANSPARENT);

        // Minimal window size set as 800x600
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}