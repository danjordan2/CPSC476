package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class QuestionsFacade extends AbstractFacade<Questions> implements ejb.QuestionsFacadeRemote {
    @PersistenceContext(unitName = "problemset3EnterpriseApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public QuestionsFacade() {
        super(Questions.class);
    }
   
}
