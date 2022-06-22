package edu.pjatk.tpo.msgappjsm;

public final class ChatModel {
    private String username;
    SenderProtocol senderProtocol;
    ReceiverProtocol receiverProtocol;

    public void setSenderProtocol(SenderProtocol senderProtocol){
        this.senderProtocol = senderProtocol;
    }

    public void setReceiverProtocol(ReceiverProtocol receiverProtocol){
        this.receiverProtocol = receiverProtocol;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void sendMessage(String message, String receiver){
        senderProtocol.send(message, receiver);
    }

    public void receiveMessage() {
        receiverProtocol.receive(username);
    }


    public void stopReceiveMessage(){
        receiverProtocol.stop();
    }
}
