package com.problemset4.mdb;

import com.problemset4.interceptor.DedupeInterceptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@JMSDestinationDefinition(name = "jms/AnswerQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "AnswerQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/AnswerQueue")
})
@Interceptors(DedupeInterceptor.class)
public class AnswerBean implements MessageListener {
    static String dbDriver = "org.apache.derby.jdbc.ClientDriver";
    static String dbUrl = "jdbc:derby://localhost:1527/Answers";
    static String dbUser = "App";
    static String dbPass = "password";
    static Connection conn;
    public AnswerBean() throws Exception{
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {                
                try{
                    conn = DriverManager.getConnection(dbUrl,dbUser, dbPass);
                    Statement stmt = conn.createStatement();
                    String msg[] = message.getBody(String.class).split("(:)|(\\-{2})");
                    String msgType = msg[0].trim();
                    String msgBody = msg[1].trim();
                    String msgUser = msg[2].trim();
                    
                    System.out.println("Received message: " + message.getBody(String.class)); 
                    
                    //Check if user exists
                    if(!stmt.executeQuery("SELECT * FROM USERS WHERE USERNAME='"+msgUser+"'").next())
                    {
                        //Insert user into database if they don't exist
                        if(stmt.executeUpdate("INSERT INTO users(ID, USERNAME, PASSWORD) VALUES(DEFAULT,'"+msgUser+"','')") > 0)
                            System.out.println("User " + msgUser + " added to database");
                    }
                    //Retrieve user ID
                    ResultSet userID = stmt.executeQuery("SELECT * FROM USERS WHERE USERNAME='"+msgUser+"'");
                    userID.next();
                    //Process question
                    if(Character.toUpperCase(msgType.charAt(0)) == 'Q'){
                        if(stmt.executeUpdate("INSERT INTO questions(ID, USER_ID, TEXT, CREATED_AT) VALUES(DEFAULT,"+userID.getInt("ID")+", '"+msgBody+"', DEFAULT)") > 0)
                            System.out.println("Question added to database");
                    }
                    //Process Answer
                    else if(Character.toUpperCase(msgType.charAt(0)) == 'A'){
                        int questionID = Integer.parseInt(msgType.substring(1));
                            
                            if(stmt.executeUpdate("INSERT INTO answers(ID, USER_ID, QUESTION_ID, TEXT, CREATED_AT) VALUES(DEFAULT,"+userID.getInt("ID")+","+ questionID +",'"+msgBody+"', DEFAULT)") > 0)
                                System.out.println("Answer added to database");                        
                    }
                    stmt.close();
                    conn.close();
                }catch(SQLException e){}
            }
        }catch (JMSException e) {}
    }
}