package com.problemset2.app;
import com.problemset2.entity.User;
import com.problemset2.entity.Question;
import com.problemset2.entity.Answer;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class dbMain {
    public static void main(String[] args) {
        //Open connection to database
        EntityManagerFactory emf =Persistence.createEntityManagerFactory("db/questions.odb");
        EntityManager em = emf.createEntityManager();
       
        //Get list of questions
        TypedQuery<Question> q1 = em.createQuery("SELECT question FROM Question question", Question.class);
        List<Question> results1 = q1.getResultList();
        //Loop through each individual question
        for(Question questions : results1)
        {
            //Get user who asked the question
            Query question_user = em.createQuery("SELECT user.username FROM User user WHERE user.id = :id");
            //Set the "id" parameter, to search for the user whos id matches the user_id of this particular question
            question_user.setParameter("id", questions.getUser_id());
            System.out.println("Question from: " + question_user.getSingleResult() + "\n");
            System.out.println(questions.getText() + "\n");
            //Count the number of answers for the question
            Query answer_count = em.createQuery("SELECT count(answer) FROM Answer answer WHERE answer.question_id = :answer_id");
            //Set the "answer_id" parameter, to search for the answers whos question_id matches the id of this particular question
            answer_count.setParameter("answer_id", questions.getId());
            System.out.println(answer_count.getSingleResult() + " answers\n");
            //Get each answer for the question
            TypedQuery<Answer> q2 = em.createQuery("SELECT answer FROM Answer answer WHERE answer.question_id = :question_id", Answer.class);
            q2.setParameter("question_id", questions.getId());
            List<Answer> results2 = q2.getResultList();
            //Loop through each individual answer given
            for(Answer answers : results2)
            {
                //Get the user who posted the answer
                Query answer_user = em.createQuery("SELECT user.username FROM User user WHERE user.id = :id");
                //Set the "id" parameter, to search for the user whos id matches the user_id of this particular answer
                answer_user.setParameter("id", answers.getUser_id());
                System.out.println(answer_user.getSingleResult() + ": " + answers.getText());
            }
            System.out.println("\n");
        }
        //Close database connection
        em.close();
        emf.close();
    }
}