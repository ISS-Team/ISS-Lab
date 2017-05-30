package cmsteam2.backend;

import cmsteam2.common.domain.Conference;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ConferenceRepository extends GenericRepository {
    public ConferenceRepository(Properties props) {
        super(props);
    }

    public boolean update(Conference conference) {
        org.hibernate.Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(conference);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (tx != null)
                tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public void save(Conference conference) {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(conference);
        session.getTransaction().commit();
        session.close();
    }

    public List<Conference> getAll() {
        org.hibernate.Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Conference> list = session.createQuery("from Conference", Conference.class).list();
            session.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null)
                tx.rollback();
            session.close();
            return null;
        }
    }
}
