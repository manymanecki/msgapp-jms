package edu.pjatk.tpo.msgappjsm;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.Properties;

public class ReceiverProtocol implements MessageListener {
    public ReceiverChatBridge receiverChatBridge;
    private String username;
    private Queue sendQueue;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    ReceiverProtocol() {
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put("jboss.naming.client.ejb.context", true);
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        try {
            Context context = new InitialContext(properties);
            sendQueue = (Queue) context.lookup("jms/SendQueue");
            connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public void setReceiverChatBridge(ReceiverChatBridge receiverChatBridge) {
        this.receiverChatBridge = receiverChatBridge;
    }

    public void receive(String username) {
        try {
            this.username = username;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            MessageConsumer messageConsumer = session.createConsumer(sendQueue, "name = '" + username + "'");
            messageConsumer.setMessageListener(this);
            connection.start();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            connection.close();
        } catch (JMSException ignore) {
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(":===[NEW MESSAGE]===:\nReceived on: " + new Date() + "\nfrom: " + username + "\n" + ((TextMessage) message).getText() + "\n:===================:");
            message.acknowledge();
            receiverChatBridge.showMessage(((TextMessage) message).getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
