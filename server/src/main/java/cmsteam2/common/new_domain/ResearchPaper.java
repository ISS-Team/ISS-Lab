package cmsteam2.common.new_domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ResearchPaper")
public class ResearchPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String abstractPaper;
    private String pathFile;

    @ManyToOne
    private User author;

    @ElementCollection
    @CollectionTable(name = "Topics", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "topics")
    private Set<String> topics = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "Keywords", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "keywords")
    private Set<String> keywords = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedPaper")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Set<User> reviewers = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Conference conference;

    public ResearchPaper() {}

    public ResearchPaper(int id, String title, User author) {
        this.id = id;
        this.title = title;
        this.author=author;
    }

    public ResearchPaper(int id, String title, String abstractPaper, User author) {
        this.id = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.author=author;
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

    public Set<User> getReviewers() {
        return reviewers;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public void setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<String> getTopics() {
        return topics;
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}


