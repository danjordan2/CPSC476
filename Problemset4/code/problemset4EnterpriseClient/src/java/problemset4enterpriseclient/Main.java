package problemset4enterpriseclient;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


public class Main {
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/AnswerQueue")
    private static Destination destination;
    public static void main(String[] args) {
        
    sendMessage("Q: Why should I study Spring framework if Java ee 7 exists and covers all capabilities which Spring implements? -- puffstone");
    try{Thread.sleep(2000);}catch(InterruptedException e){}
    sendMessage("A2: I think that one simply should know both of them. -- ProfAvery");
    try{Thread.sleep(2000);}catch(InterruptedException e){}
    sendMessage("A2: I think that one simply should know both of them. -- dogpockets");
    try{Thread.sleep(2000);}catch(InterruptedException e){}
    sendMessage("A2: Lets try another answer then. -- dogpockets");


    }
    public static void sendMessage(String message){
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage(message);
            messageProducer.send(textMessage);
            connection.close();
        }catch (JMSException ex) {
            System.out.println("Error connecting to message queue");
        }
    }
}