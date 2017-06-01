package cmsteam2.backend;

import cmsteam2.common.new_domain.ResearchPaper;
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
}
