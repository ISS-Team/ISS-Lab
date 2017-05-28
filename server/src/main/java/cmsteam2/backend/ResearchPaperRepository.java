package cmsteam2.backend;

import cmsteam2.common.domain.Session;
import cmsteam2.common.domain.ResearchPaper;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ResearchPaperRepository extends GenericRepository {

    public ResearchPaperRepository(Properties props) {
        super(props);
    }

    public void update(ResearchPaper researchPaper) {
        org.hibernate.Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(researchPaper);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    public void save(ResearchPaper researchPaper) {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(researchPaper);
        session.getTransaction().commit();
        session.close();
    }
}
