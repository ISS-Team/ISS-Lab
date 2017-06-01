package cmsteam2.backend;

import cmsteam2.common.new_domain.Session;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class SessionRepository extends GenericRepository<Session> {
    public SessionRepository(Properties props) {
        super(props);
    }

    public List<Session> GetAllByConference(int conferenceId) {
        List<Session> sessions = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            sessions = session.createQuery("From Session where Session.conference.id = " + conferenceId, Session.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sessions;

    }
}
