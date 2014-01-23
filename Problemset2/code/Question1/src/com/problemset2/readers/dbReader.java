package com.problemset2.readers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbReader {
        static String dbDriver = "org.hsqldb.jdbcDriver"; 
        static String dbUrl = "jdbc:hsqldb:hsql://localhost/cpsc476;ifexists=true";
        static String dbUser = "SA";
        static String dbPass = "Passw0rd";
        static Connection conn;

    public static void main(String[] args) throws Exception {
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl,dbUser, dbPass);
        Statement stmt = conn.createStatement();

        ResultSet questions = stmt.executeQuery("SELECT username, questions.id, text FROM users, questions WHERE user_id = id;");
        ResultSet answers;
        ResultSet resultCount;        
        while (questions.next())
        {
            System.out.println("Question from " + questions.getString("USERNAME")+":\n\n" + questions.getString("TEXT")+"\n");
            resultCount = stmt.executeQuery("SELECT count(*) AS total FROM users, answers WHERE id = user_id AND question_id = "+questions.getInt("ID")+";");                            
            answers = stmt.executeQuery("SELECT username, text, question_id FROM users, answers WHERE id = user_id AND question_id = "+questions.getInt("ID")+";");                
            while (resultCount.next())
                System.out.println(resultCount.getInt("total")+" answers \n");
            while (answers.next())
            {
                System.out.println(answers.getString("USERNAME")+": " + answers .getString("TEXT"));
            }
            System.out.println("\n---\n");
            answers.close();
            resultCount.close();
        }
        questions.close();
        stmt.close();
        conn.close();
    }
}