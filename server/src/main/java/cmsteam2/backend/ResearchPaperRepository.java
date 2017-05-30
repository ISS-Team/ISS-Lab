package cmsteam2.backend;

import cmsteam2.common.domain.ResearchPaper;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ResearchPaperRepository extends GenericRepository {

    public ResearchPaperRepository(Properties props) {
        super(props);
    }

    public boolean update(ResearchPaper researchPaper) {
        org.hibernate.Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(researchPaper);
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

    public List<ResearchPaper> getAll() {
        org.hibernate.Session session = sessionFactory.openSession();
        List<ResearchPaper> ResearchPapers = null;
        try {
            ResearchPapers = session.createQuery("From ResearchPaper ", ResearchPaper.class).list();
            System.out.println(ResearchPapers.size());
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return ResearchPapers;
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
