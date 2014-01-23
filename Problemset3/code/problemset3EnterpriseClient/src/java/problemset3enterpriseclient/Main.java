package problemset3enterpriseclient;

import ejb.HelloWorldBeanRemote;
import ejb.Questions;
import ejb.QuestionsFacadeRemote;
import java.util.List;
import javax.ejb.EJB;

public class Main {
    @EJB
    private static QuestionsFacadeRemote questionsFacade;
    @EJB
    private static HelloWorldBeanRemote helloWorldBean;

    public static void main(String[] args) {
        System.out.println(helloWorldBean.hello("Dan"));
        
        List<Questions> questions = questionsFacade.findAll();
        for(Questions question : questions)
        {
            System.out.println("Question from " + question.getUserId().getUsername() + ": \n");
            System.out.println(question.getText() + "\n"); 
        }  
    }
}