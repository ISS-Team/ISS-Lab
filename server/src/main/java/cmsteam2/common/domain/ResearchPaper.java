package cmsteam2.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Topics", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "topics")
    private Set<String> topics = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Keywords", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "keywords")
    private Set<String> keywords = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewedPaper", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username", fetch = FetchType.EAGER)
    private Set<User> reviewers = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty("conference_id")
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

    public Conference getConference() {
        return conference;
    }

    @JsonIgnore
    public boolean isAccepted() {
        int sum = 0;
        for (Review r : reviews) {
            sum += r.getQualifier().weight;
        }
        return sum > 0;
    }
}


