package cmsteam2.backend;

import cmsteam2.common.domain.Review;
import org.hibernate.Session;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class ReviewRepository extends GenericRepository<Review> {
    public ReviewRepository(Properties props) {
        super(props);
    }

    public List<Review> getAll(int paperId) {
        try (Session s = sessionFactory.openSession()) {
            return s.createQuery("from Review R where R.reviewedPaper.id = " + paperId, Review.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> getAllByConference(int conferenceId) {
        try (Session s = sessionFactory.openSession()) {
            return s.createQuery("from Review R where R.reviewedPaper.conference.id = " + conferenceId, Review.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> get(String username) {
        List<Review> reviews = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            reviews = session.createQuery("from Review where reviewer.username like '" + username + "'", Review.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public Review get(String username, int paperId) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("from Review where reviewer.username like '" + username + "' and reviewedPaper.id = " + paperId, Review.class).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
