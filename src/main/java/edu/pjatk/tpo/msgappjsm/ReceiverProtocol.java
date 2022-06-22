package edu.pjatk.tpo.msgappjsm;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ReceiverProtocol implements MessageListener {
    private String username;
    private Queue sendQueue;
    private Queue receiveQueue;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;

    ReceiverProtocol(){
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put("jboss.naming.client.ejb.context", true);
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        try {
            Context context = new InitialContext(properties);
            sendQueue = (Queue) context.lookup("jms/SendQueue");
            receiveQueue = (Queue) context.lookup("jms/ReceiveQueue");
            connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        }catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public void receive(){
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer messageConsumer = session.createConsumer(sendQueue, "name = '"+username+"'");
            messageConsumer.setMessageListener(this);
            connection.start();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void stop(){
        try {
            System.out.println("Connection for " + username + " has been closed.");
            connection.close();
        } catch (JMSException ignore) {}
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUsername(String username){
        this.username = username;
    }
}
