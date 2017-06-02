package cmsteam2.backend;

import cmsteam2.common.domain.Conference;
import cmsteam2.common.domain.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

/**
 * Created by User on 02/06/2017.
 */
public class ReviewRepository extends GenericRepository<Review> {

    public ReviewRepository(Properties props) {
        super(props);
    }

    public List<Review> getAllReviewForUser(String username) {
        List<Review> reviews = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            reviews = session.createQuery("From Review where reviewer.username ='" + username + "'", Review.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

}
