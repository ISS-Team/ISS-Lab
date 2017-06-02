package cmsteam2.backend;

import cmsteam2.common.domain.Conference;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ConferenceRepository extends GenericRepository<Conference> {
    public ConferenceRepository(Properties props) {
        super(props);
    }

    public List<Conference> getAll() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Conference> list = session.createQuery("from Conference", Conference.class).list();
            session.close();
            return list;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(ex);
        }
    }
    public Conference getById(int id){
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Conference conference = session.createQuery("from Conference where id="+id, Conference.class).getSingleResult();
            session.close();
            return conference;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(ex);
        }

    }
}
