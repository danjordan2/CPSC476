package com.problemset2.app;
import com.problemset2.entity.User;
import com.problemset2.entity.Question;
import com.problemset2.entity.Answer;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class dbMain {
    public static void main(String[] args) {
        User user;
        Question question;
        Answer answer;

        EntityManagerFactory emf =Persistence.createEntityManagerFactory("db/questions.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("Inserting data......\n\n");       
        user = new User(1, "fishygut", "password");
        em.persist(user);
        user = new User(2, "puffstone", "qwerty");
        em.persist(user);
        user = new User(3, "barkingcustard", "welcome");
        em.persist(user);

        question = new Question(1, 1, "What are the main differences between Java EE 7 and Java EE 6 ?");
        em.persist(question);
        question = new Question(2, 3, "Where can I find code examples for Java 7 EE Tutorial?");
        em.persist(question);

        answer = new Answer(2, 1, "Support for JSON");
        em.persist(answer);
        answer = new Answer(3, 1, "GlassFish v4");
        em.persist(answer);
        answer = new Answer(2, 1, "Improved Bean Validation");
        em.persist(answer);
        answer = new Answer(3, 1, "WebSocket support");
        em.persist(answer);
        answer = new Answer(2, 2, "https://java.net/projects/javaeetutorial/sources/svn/show/trunk/examples");
        em.persist(answer);
        answer = new Answer(3, 2, "Thanks!");
        em.persist(answer);

        em.getTransaction().commit();

        System.out.println("Verifying data......\n\n");

        System.out.println("Users");
        TypedQuery<User> q1 = em.createQuery("SELECT user FROM User user", User.class);
        List<User> results1 = q1.getResultList();
        for(User users : results1)
        {
            System.out.println("Id: " + users.getId() + ", Username: " + users.getUsername() + ", Password: " + users.getPassword());
        }

        System.out.println("\nQuestions");
        TypedQuery<Question> q2 = em.createQuery("SELECT question FROM Question question", Question.class);
        List<Question> results2 = q2.getResultList();
        for(Question questions : results2)
        {
            System.out.println("ID: " + questions.getId() + ", User ID: " + questions.getUser_id() + ", Text: " + questions.getText());
        }

        System.out.println("\nAnswers");
        TypedQuery<Answer> q3 = em.createQuery("SELECT answer FROM Answer answer", Answer.class);
        List<Answer> results3 = q3.getResultList();
        for(Answer answers : results3)
        {
            System.out.println("User ID: " + answers.getUser_id() + ", Question ID: " + answers.getQuestion_id() + ", Text: " + answers.getText());
        }

        System.out.println("\nClosing database connection......\n\n");
        em.close();
        emf.close();
    }
}