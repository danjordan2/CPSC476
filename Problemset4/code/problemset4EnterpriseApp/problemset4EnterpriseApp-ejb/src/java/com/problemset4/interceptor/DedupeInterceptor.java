package com.problemset4.interceptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.jms.Message;

@Interceptor
public class DedupeInterceptor {
    static String dbDriver = "org.apache.derby.jdbc.ClientDriver";
    static String dbUrl = "jdbc:derby://localhost:1527/Answers";
    static String dbUser = "App";
    static String dbPass = "password";
    static Connection conn;
    
    
    @AroundInvoke
    public Object interceptMsg(InvocationContext ctx) throws Exception {
        conn = DriverManager.getConnection(dbUrl,dbUser, dbPass);
        Statement stmt = conn.createStatement();
        Message message = (Message)ctx.getParameters()[0];
        String msg[] = message.getBody(String.class).split("(:)|(\\-{2})");
        String msgType = msg[0].trim();
        String msgBody = msg[1].trim();
        
        System.out.println("Intecepting message: " + message.getBody(String.class));
        if(Character.toUpperCase(msgType.charAt(0)) == 'A')
        {
            int questionID = Integer.parseInt(msgType.substring(1));
            //Check if answer exists in database
            if(stmt.executeQuery("SELECT * FROM answers WHERE QUESTION_ID ="+questionID+" AND TEXT='"+msgBody+"'").next())
            {
                //Answer exists
                System.out.println("Answer already exists in database");
                return 0;
            }            
        }
        System.out.println("Proceeding to MDB...");
        return ctx.proceed();
    }
}
