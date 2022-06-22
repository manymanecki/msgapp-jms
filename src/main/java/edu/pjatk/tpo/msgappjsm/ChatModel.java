package edu.pjatk.tpo.msgappjsm;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public final class ChatModel {
    private String username;
    SenderProtocol senderProtocol;
    ReceiverProtocol receiverProtocol;

    ChatModel(){
        senderProtocol = new SenderProtocol();
        receiverProtocol = new ReceiverProtocol();
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void sendMessage(String message, String receiver){
        senderProtocol.setUsername(username);
        senderProtocol.send(message, receiver);
    }

    public void receiveMessage() {
        receiverProtocol.setUsername(username);
        receiverProtocol.receive();
    }

    public void stopReceiveMessage(){
        receiverProtocol.stop();
    }
}
