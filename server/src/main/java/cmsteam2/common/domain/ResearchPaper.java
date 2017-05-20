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
    private String id;
    private String title;
    private String text;


    @ElementCollection
    @CollectionTable(name="MetaData",joinColumns = @JoinColumn(name="id_ResearchPaper"))
    @Column(name = "metaData")
    private Set<String> metaData = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedPaper")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "researchPaper")
    private Set<Reviewer> revieweri = new HashSet<>();


    public ResearchPaper(String id, String title, User author) {
        this.id = id;
        this.title = title;
//        this.author=author;
    }

    public ResearchPaper(String id, String title, String text, User author) {
        this.id = id;
        this.title = title;
        this.text = text;
//        this.author=author;
    }

    public ResearchPaper() {

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }




    public void setText(String text) {
        this.text = text;
    }

    public void addMetaData(String meta) {
        this.metaData.add(meta);
    }


    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public Set<String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Set<String> metaData) {
        this.metaData = metaData;
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


