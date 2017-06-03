package cmsteam2.rest.controller;

import cmsteam2.backend.*;
import cmsteam2.common.domain.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/conferences/{conferenceId}/papers")
@Transactional
public class ResearchPaperController {

    private ResearchPaperRepository researchPaperRepository;
    private BiddingRepository biddingRepository;
    private ReviewRepository reviewRepository;
    private ConferenceRepository conferenceRepository;

    public ResearchPaperController() {
        researchPaperRepository = new ResearchPaperRepository(GenericRepository.loadProps());
        biddingRepository = new BiddingRepository(GenericRepository.loadProps());
        reviewRepository = new ReviewRepository(GenericRepository.loadProps());
        conferenceRepository = new ConferenceRepository(GenericRepository.loadProps());
    }

    private boolean checkPaper(ResearchPaper paper) {
        return (paper.getTopics().size() != 0 && paper.getKeywords().size() != 0 && !(paper.getAbstractPaper().equals("")));
    }

    @PostMapping
    @RequestMapping("/save")
    public ResponseEntity save(@PathVariable int conferenceId, @RequestBody ResearchPaper paper, @SessionAttribute("username") String username) {
        if (checkPaper(paper)) {
            paper.setAuthor(researchPaperRepository.getUser(username));
            paper.setConference(conferenceRepository.getById(conferenceId));
            researchPaperRepository.save(paper);
            return ResponseEntity.ok("{}");
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody ResearchPaper paper) {
        if (checkPaper(paper))
            researchPaperRepository.update(paper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<ResearchPaper> getAll(@PathVariable int conferenceId) {
        return researchPaperRepository.getAll(conferenceId);
    }

    @GetMapping("/getaccepted")
    public List<ResearchPaper> getAllAccepted(@PathVariable int conferenceId) {
        List<ResearchPaper> papers = researchPaperRepository.getAll(conferenceId);
        papers.removeIf(paper -> !paper.isAccepted());
        return papers;
    }

    @PostMapping("/bid/{paperId}")
    public void bid(@PathVariable int paperId, HttpEntity<String> body, @SessionAttribute("username") String username) {
        try {
            JSONObject json = new JSONObject(body.getBody());
            researchPaperRepository.bid(username, paperId, Bidding.Status.valueOf(json.getString("status")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/gettoreview")
//    public List<ResearchPaper> getAllToReview(@PathVariable int id, @SessionAttribute("username") String username) {
//        List<ResearchPaper> papers = researchPaperRepository.getAll(id);
//        User user = researchPaperRepository.getUser(username);
//        papers.removeIf(paper -> user.getBiddings().stream().anyMatch(bid -> bid.getPaper().getId() == paper.getId() && bid.getStatus() == Bidding.Status.REJECTED));
//        return null;
//    }

    @GetMapping("/getincomplete")
    public List<Review> getAllReviewIncomplete(@SessionAttribute("username") String username) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : reviewRepository.get(username)) {
            if (review.getDate().getTime() == -1) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    @GetMapping("/getreviewers/{paperId}")
    public List<User> getReviewers(@PathVariable int paperId) {
        ResearchPaper paper = researchPaperRepository.get(paperId);
        List<Bidding> allBidding = biddingRepository.getAllByResearchPaperId(paperId);
        List<User> result = new ArrayList<>();
        List<Bidding> reviewers = new ArrayList<>();
        for (Bidding bid : allBidding) {
            if (bid.getStatus() != Bidding.Status.REJECTED) {
                reviewers.add(bid);
            }
        }
        if (reviewers.size() == 0) {
            Random random = new Random(1);
//           User victim = ((Bidding) paper.getBidders().toArray()[random.nextInt(paper.getBidders().size())]).getUser();
            int rand = random.nextInt(allBidding.size());
            User victim = ((Bidding) allBidding.toArray()[rand]).getUser();
            result.add(victim);
        } else {
            if (reviewers.size() > paper.getConference().getReviewersPerPaper()) {
                reviewers.sort(Comparator.comparingInt(u -> u.getStatus().weight));
                for (Bidding b : reviewers.subList(0, paper.getConference().getReviewersPerPaper())) {
                    result.add(b.getUser());
                }
            } else {
                for (Bidding b : reviewers) {
                    result.add(b.getUser());
                }
            }
        }
        for (User reviewer : result) {
            if (reviewRepository.get(reviewer.getUsername(), paperId) == null) {
                Review review = new Review();
                review.setDate(new Date(-1));
                review.setReviewer(reviewer);
                review.setQualifier(Qualifier.BORDERLINE);
                reviewRepository.save(review);
            }
        }
        return result;
    }

}
