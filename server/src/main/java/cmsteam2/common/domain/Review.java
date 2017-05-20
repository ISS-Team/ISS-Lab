package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 5/10/2017.
 */
/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name = "Review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Review;

    @ManyToOne(cascade = CascadeType.ALL)
    private User reviewer;

    private Date date;

    @Enumerated(EnumType.STRING)
    private Qualifier qualifier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<Recommendation> recommendations = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private ResearchPaper reviewedPaper;

    public Review() {
    }


    public Qualifier getQualifier() {
        return qualifier;
    }


    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }


    public Review(Date date, Qualifier qualifier) {
        this.date = date;
        this.qualifier = qualifier;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public ResearchPaper getReviewedPaper() {
        return reviewedPaper;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void setReviewedPaper(ResearchPaper reviewedPaper) {
        this.reviewedPaper = reviewedPaper;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
