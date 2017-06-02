package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ReviewRepository;
import cmsteam2.common.domain.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 02/06/2017.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewRepository reviewRepository=new ReviewRepository(GenericRepository.loadProps());
    @GetMapping("/getReviewsIncomplete/{username}")
    public List<Review> getAllReviewIncomplete(@PathVariable String username){
        System.out.println(username);
        List<Review> reviews=new ArrayList<>();
        for (Review review:reviewRepository.getAllReviewForUser(username)) {
            if (review.getQualifier()==null){
                reviews.add(review);
            }

        }
        return reviews;
    }
}
