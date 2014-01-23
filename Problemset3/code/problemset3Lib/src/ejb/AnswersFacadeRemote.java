package ejb;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AnswersFacadeRemote {

    void create(Answers answers);

    void edit(Answers answers);

    void remove(Answers answers);

    Answers find(Object id);

    List<Answers> findAll();

    List<Answers> findRange(int[] range);

    int count();
    
}
