package cmsteam2.rest.controller;

import cmsteam2.backend.BiddingRepository;
import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ResearchPaperRepository;
import cmsteam2.backend.ReviewRepository;
import cmsteam2.common.domain.Bidding;
import cmsteam2.common.domain.Qualifier;
import cmsteam2.common.domain.Review;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/conferences/{conferenceId}/papers/{paperId}/reviews")
public class ReviewController {

    private ResearchPaperRepository researchPaperRepository;
    private BiddingRepository biddingRepository;
    private ReviewRepository reviewRepository;

    public ReviewController() {
        researchPaperRepository = new ResearchPaperRepository(GenericRepository.loadProps());
        biddingRepository = new BiddingRepository(GenericRepository.loadProps());
        reviewRepository = new ReviewRepository(GenericRepository.loadProps());
    }

    @PostMapping("/review")
    public ResponseEntity review(@PathVariable int paperId, @PathVariable int conferenceId, HttpEntity<String> body, @SessionAttribute("username") String username) {
        try {
            JSONObject json = new JSONObject(body.getBody());
            Review review = reviewRepository.get(username, paperId);
            Date date = new Date(json.getInt("date"));
            Qualifier q = Qualifier.valueOf(json.getString("qualifier"));
            review.setDate(date);
            review.setQualifier(q);
            reviewRepository.update(review);
            verifyFinished(conferenceId);
            return ResponseEntity.ok("{}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getall")
    public List<Review> getAll(@PathVariable int paperId) {
        return reviewRepository.getAll(paperId);
    }

    private void verifyFinished(int conference) {
        List<Review> reviews = reviewRepository.getAllByConference(conference);
        boolean isFinished = true;
        for (Review r : reviews) {
            if (r.getDate().getTime() == -1) {
                isFinished =         false;
            }
        }
        if (isFinished) {
            try {
                SessionController.instance.makeSessionsVersion2(conference);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
