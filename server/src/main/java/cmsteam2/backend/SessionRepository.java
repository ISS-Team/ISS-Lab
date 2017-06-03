package cmsteam2.backend;

import cmsteam2.common.domain.Participation;
import cmsteam2.common.domain.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
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

    public void participate(int sessionId, String username, Participation.Status status) {
        try(org.hibernate.Session session = sessionFactory.openSession()) {
            Session s = session.createQuery("from Session S where id = " + sessionId, Session.class).uniqueResult();
            Optional<Participation> opt = session.createQuery("from Participation P where user.username like '" + username + "' and session.id = " + sessionId, Participation.class).uniqueResultOptional();
            if (opt.isPresent()) {
                Participation p = opt.get();
                p.setStatus(status);
                session.update(p);
            } else {
                Participation p = new Participation();
                p.setSession(s);
                p.setUser(getUser(username));
                p.setStatus(status);
                session.save(p);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
