package cmsteam2.common.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 5/10/2017.
 */
public class ResearchPaper {
    private String id;
    private String title;
    private String text;
    private List<String> metaData = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<User> authors = new ArrayList<>();

    public ResearchPaper(String id, String title,User author) {
        this.id = id;
        this.title = title;
        this.authors.add(author);
    }

    public ResearchPaper(String id, String title, String text, User author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authors.add(author);
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

    public List<String> getMetaData() {
        return metaData;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public List<User> getReviewers(){
        List<User> reviewers = new ArrayList<>();
        for(Review review:this.reviews){
            reviewers.add(review.getReviewer());
        }
        return reviewers;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addMetaData(String meta){
        this.metaData.add(meta);
    }

    public void addAuthor(User author){
        this.authors.add(author);
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }
}
