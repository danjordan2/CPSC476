package ejb;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface QuestionsFacadeRemote {

    void create(Questions questions);

    void edit(Questions questions);

    void remove(Questions questions);

    Questions find(Object id);

    List<Questions> findAll();

    List<Questions> findRange(int[] range);

    int count();
    
}
