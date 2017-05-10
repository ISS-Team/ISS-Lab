package cmsteam2.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 5/10/2017.
 */
public class Review {

    private User reviewer;
    private String id;
    private Date date;
    private Qualifier qualifier;
    private List<Recommendation> recommendations = new ArrayList<>();
    private ResearchPaper reviewedPaper;

    public Review(User reviewer, String id, Date date, Qualifier qualifier, ResearchPaper reviewedPaper) {
        this.reviewer = reviewer;
        this.id = id;
        this.date = date;
        this.qualifier = qualifier;
        this.reviewedPaper = reviewedPaper;
    }

    public User getReviewer() {
        return reviewer;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public ResearchPaper getReviewedPaper() {
        return reviewedPaper;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public void addRecommendation(Recommendation recommendation){
        recommendations.add(recommendation);
    }

    public void clearRecommendations(){
        recommendations.clear();
    }
}
