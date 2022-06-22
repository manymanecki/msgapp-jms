package edu.pjatk.tpo.msgappjsm;

public class ReceiverChatBridge {
    private ChatController chatController;

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    public void showMessage(String message){
        chatController.showReceiverMessage(message);
    }
}
