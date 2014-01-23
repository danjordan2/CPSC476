package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AnswersFacade extends AbstractFacade<Answers> implements AnswersFacadeRemote {
    @PersistenceContext(unitName = "problemset3EnterpriseApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswersFacade() {
        super(Answers.class);
    }
    
}
