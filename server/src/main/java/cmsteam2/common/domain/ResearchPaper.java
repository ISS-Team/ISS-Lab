package cmsteam2.common.domain;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 5/10/2017.
 */

/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name = "ResearchPaper")
public class ResearchPaper {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String abstractPaper;
    private String pathFile;


    @OneToOne
    @JoinColumn(name="frn_ResearchPaper_Id")
    private MetaData metaData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedPaper")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "researchPaper")
    private Set<Reviewer> revieweri = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Conference conference;


    public ResearchPaper(int id, String title, User author) {
        this.id = id;
        this.title = title;
//        this.author=author;
    }

    public ResearchPaper(int id, String title, String abstractPaper, User author) {
        this.id = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
//        this.author=author;
    }

    public ResearchPaper() {

    }

    public int getId() {
        return id;
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


