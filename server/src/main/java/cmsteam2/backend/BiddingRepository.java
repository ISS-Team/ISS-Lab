package cmsteam2.backend;

import cmsteam2.common.domain.Bidding;
import cmsteam2.common.domain.Conference;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

/**
 * Created by User on 02/06/2017.
 */
public class BiddingRepository extends GenericRepository<Bidding>{
    public BiddingRepository(Properties props) {
        super(props);
    }
    public List<Bidding> getAllByResearchPaperId(int id){
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Bidding> list = session.createQuery("from Bidding where paper_id="+id, Bidding.class).list();
            session.close();
            return list;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(ex);
        }
    }
}
