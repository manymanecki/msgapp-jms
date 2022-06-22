package edu.pjatk.tpo.msgappjsm;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class SenderProtocol {
    private Queue sendQueue;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    SenderProtocol() {
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

    public void send(String message, String receiver) {
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setObjectProperty("name", receiver);
            MessageProducer messageProducer = session.createProducer(sendQueue);
            messageProducer.send(textMessage);
            System.out.println("Sent: " + message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException ignored) {
            }
        }
    }
}
