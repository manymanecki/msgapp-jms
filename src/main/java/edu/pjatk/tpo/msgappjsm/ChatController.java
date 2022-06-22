package edu.pjatk.tpo.msgappjsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;

public class ChatController {

    private ChatModel chatModel;

    private ViewModel viewModel;

    @FXML
    private TextField textField;

    @FXML
    private Text usernameText;

    @FXML
    private TextField messageToField;

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void setUserModel(ChatModel chatModel){ this.chatModel = chatModel; }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) {
        chatModel.stopReceiveMessage();
        viewModel.setCurrentView(ViewModel.View.LOGIN);
        textField.clear();
    }

    @FXML
    private void onSendButtonClick(ActionEvent event) {
        if(textField.getText().length() == 0){
            System.out.println("Empty message, dont spam!");
        }else if (messageToField.getText().length() == 0) {
            System.out.println("Who do you want to send the message to?");
        } else if (Objects.equals(messageToField.getText(), usernameText.getText())){
            System.out.println("Why would you send messages to yourself?");
        } else {
            System.out.println("Button has been clicked! Message should be sent!");
            chatModel.sendMessage(textField.getText(), messageToField.getText());
            textField.clear();
        }
    }

    public void setUsernameText(){
        usernameText.setText(chatModel.getUsername());
    }
}