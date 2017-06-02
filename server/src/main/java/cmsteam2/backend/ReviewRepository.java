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
}
