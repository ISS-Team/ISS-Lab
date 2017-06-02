package cmsteam2.backend;

import cmsteam2.common.domain.Review;

import java.util.Properties;

public class ReviewRepository extends GenericRepository<Review> {
    public ReviewRepository(Properties props) {
        super(props);
    }
}
