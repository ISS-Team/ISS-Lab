package cmsteam2.backend;

import cmsteam2.common.domain.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class SessionRepository extends GenericRepository<Session> {
    public SessionRepository(Properties props) {
        super(props);
    }

    public List<Session> getAllByConference(int conferenceId) {
        List<Session> sessions = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Query<Session> q = session.createQuery("from Session S where S.conference.id = " + conferenceId, Session.class);
            sessions = q.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }
}
