package cmsteam2.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"researchPapers", "sessions", "biddings"})
    private User reviewer;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Qualifier qualifier;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Recommendations", joinColumns = @JoinColumn(name = "id_Recommendations"))
    @Column(name = "recommendations")
    private List<String> recommendations = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private ResearchPaper reviewedPaper;

    public Review() {}

    public Review(Date date, Qualifier qualifier) {
        this.date = date;
        this.qualifier = qualifier;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public void setQualifier(Qualifier qualifier) {
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

    public List<String> getRecommendations() {
        return recommendations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReviewedPaper(ResearchPaper reviewedPaper) {
        this.reviewedPaper = reviewedPaper;
    }
}
