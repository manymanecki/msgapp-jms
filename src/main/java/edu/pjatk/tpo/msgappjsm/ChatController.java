package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.synedra.validatorfx.Validator;

import java.util.Objects;

public class ChatController {
    Validator validator = new Validator();
    @FXML
    public VBox vboxChat;
    @FXML
    private ChatModel chatModel;
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField textField;
    @FXML
    private Text usernameText;
    @FXML
    private TextField messageToField;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setUserModel(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
        System.out.println("User: " + usernameText.getText() + " has left the chatroom. Goodbye!\n");
        chatModel.stopReceiveMessage();
        vboxChat.getChildren().clear();
        viewModel.setCurrentView(ViewModel.View.LOGIN);
        textField.clear();
        messageToField.clear();
    }

    @FXML
    private void onSendButtonClick(ActionEvent event) {
        if (textField.getText().length() == 0) {
            System.out.println("Empty message, dont spam.");
        } else if (messageToField.getText().length() == 0) {
            System.out.println("Who do you want to send the message to?");
        } else if (Objects.equals(messageToField.getText(), usernameText.getText())) {
            System.out.println("Why would you send messages to yourself?");
        } else {
            chatModel.sendMessage(textField.getText(), messageToField.getText());
            showSenderMessage(textField.getText());
            textField.clear();
        }
    }

    public void setUsernameText() {
        usernameText.setText(chatModel.getUsername());
    }

    public void showSenderMessage(String message) {
        Label sentMessage = new Label();
        sentMessage.setId("sent-message");
        sentMessage.setText(message);
        sentMessage.setWrapText(true);

        HBox hboxLine = new HBox();
        hboxLine.setId("hbox-line");
        hboxLine.setAlignment(Pos.CENTER_RIGHT);

        vboxChat.getChildren().add(hboxLine);
        hboxLine.getChildren().add(sentMessage);
    }

    public void showReceiverMessage(String message) {
        Label receivedMessage = new Label();
        receivedMessage.setId("received-message");
        receivedMessage.setText(message);
        receivedMessage.setWrapText(true);

        HBox hboxLine = new HBox();
        hboxLine.setId("hbox-line");
        hboxLine.setAlignment(Pos.CENTER_LEFT);

        textField.setText(message);

        vboxChat.getChildren().add(hboxLine);
        hboxLine.getChildren().add(receivedMessage);
    }
}