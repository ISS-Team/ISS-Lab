package cmsteam2.backend;

import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.Session;
import cmsteam2.common.domain.Conference;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class SessionRepository extends GenericRepository {
    public SessionRepository(Properties props) {
        super(props);
    }

    public boolean update(Session Sesiune) {
        org.hibernate.Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(Sesiune);
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

    public void save(Session Sesiune) {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Sesiune);
        session.getTransaction().commit();
        session.close();
    }

    //returneaza toate sesiunile unei conferinte

    public List<Session> GetAllByConference(int Conference_id) {
        org.hibernate.Session session = sessionFactory.openSession();
        List<Session> Sessions = null;
        try {
            Sessions = session.createQuery("From Session where Session.conference.id = " + Conference_id, Session.class).list();
            System.out.println(Sessions.size());
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return Sessions;
        }

    }
}
