package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ResearchPaper")
public class ResearchPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResearchPaper;
    private String title;
    private String abstractPaper;
    private String pathFile;

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public void setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @OneToOne
    @JoinColumn(name = "frn_ResearchPaper_Id")
    private MetaData metaData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedPaper")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "researchPaper")
    private Set<Reviewer> revieweri = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Conference conference;

    public ResearchPaper() {
    }

    public ResearchPaper(int id, String title, User author) {
        this.idResearchPaper = id;
        this.title = title;
//        this.author=author;
    }

    public ResearchPaper(int id, String title, String abstractPaper, User author) {
        this.idResearchPaper = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
//        this.author=author;
    }

    public int getIdResearchPaper() {
        return idResearchPaper;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return abstractPaper;
    }


    public void addReview(Review review) {
        this.reviews.add(review);
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Reviewer> getRevieweri() {
        return revieweri;
    }

    public void setRevieweri(Set<Reviewer> revieweri) {
        this.revieweri = revieweri;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
}


