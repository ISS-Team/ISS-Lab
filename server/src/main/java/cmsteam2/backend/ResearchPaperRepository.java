package cmsteam2.backend;

import cmsteam2.common.domain.Bidding;
import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ResearchPaperRepository extends GenericRepository<ResearchPaper> {

    public ResearchPaperRepository(Properties props) {
        super(props);
    }

    public List<ResearchPaper> getAll(int conferenceId) {
        List<ResearchPaper> researchPapers = null;
        try (Session session = sessionFactory.openSession()) {
            researchPapers = session.createQuery("from ResearchPaper RP where RP.conference.id = " + conferenceId, ResearchPaper.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return researchPapers;
    }


    public void bid(int conferenceId, String username, int paperId, Bidding.Status status) {
        try (Session session = sessionFactory.openSession()) {
            ResearchPaper paper = session.createQuery("from ResearchPaper RP where id = " + paperId, ResearchPaper.class).uniqueResult();
            User user = session.createQuery("from User U where id like '" + username + "'", User.class).uniqueResult();
            Bidding b = new Bidding();
            b.setStatus(status);
            b.setPaper(paper);
            b.setUser(user);
            session.beginTransaction();
            session.save(b);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResearchPaper get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ResearchPaper RP where id = " + id, ResearchPaper.class).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
