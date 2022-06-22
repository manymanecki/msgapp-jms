package edu.pjatk.tpo.msgappjsm;

public final class ChatModel {
    SenderProtocol senderProtocol;
    ReceiverProtocol receiverProtocol;
    private String username;

    public void setSenderProtocol(SenderProtocol senderProtocol) {
        this.senderProtocol = senderProtocol;
    }

    public void setReceiverProtocol(ReceiverProtocol receiverProtocol) {
        this.receiverProtocol = receiverProtocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void sendMessage(String message, String receiver) {
        senderProtocol.send(message, receiver);
    }

    public void receiveMessage() {
        receiverProtocol.receive(username);
    }

    public void stopReceiveMessage() {
        receiverProtocol.stop();
    }
}
