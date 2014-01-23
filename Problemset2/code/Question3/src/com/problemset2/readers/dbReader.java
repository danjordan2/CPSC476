package com.problemset2.readers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbReader implements IReader {
        static String dbDriver = "org.hsqldb.jdbcDriver"; 
        static String dbUrl = "jdbc:hsqldb:hsql://localhost/cpsc476;ifexists=true";
        static String dbUser = "SA";
        static String dbPass = "Passw0rd";
        static Connection conn;

    public String read() {
        StringBuilder sb = new StringBuilder();
        try{
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl,dbUser, dbPass);
            Statement stmt = conn.createStatement();

            ResultSet questions = stmt.executeQuery("SELECT username, questions.id, text FROM users, questions WHERE user_id = id;");
            ResultSet answers;
            ResultSet resultCount;        
            sb.append("<html>\n<head>\n<title>Daniel Jordan Problemset2 question #3</title>\n</head>\n<body>\n");
            while (questions.next())
            {
                sb.append("<p><b>Question from " + questions.getString("USERNAME")+":</b> " + questions.getString("TEXT")+"</p>\n");
                resultCount = stmt.executeQuery("SELECT count(*) AS total FROM users, answers WHERE id = user_id AND question_id = "+questions.getInt("ID")+";");                            
                answers = stmt.executeQuery("SELECT username, text, question_id FROM users, answers WHERE id = user_id AND question_id = "+questions.getInt("ID")+";");                
                while (resultCount.next())
                    sb.append("<p>"+resultCount.getInt("total")+" answers</p>\n");
                while (answers.next())
                {
                    sb.append("<li style=\"list-style-type: none;\"><b>"+answers.getString("USERNAME")+":</b> " + answers .getString("TEXT")+"</li>\n");
                }
                sb.append("<hr/>\n");
                answers.close();
                resultCount.close();
            }
            questions.close();
            stmt.close();
            conn.close();

        }catch(Exception e)
        {
            System.out.println(e);
        }
        sb.append("</body>\n</html>");
        return sb.toString();
    }
}