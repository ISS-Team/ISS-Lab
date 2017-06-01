package cmsteam2.common.domain;

import javax.persistence.*;

@Entity
@Table(name = "Recommendation")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecommendation;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Review", referencedColumnName = "Id_Review")
    private Review review;

    public Recommendation() {
    }

    public Recommendation(String description, Review review) {
        this.description = description;
        this.review = review;
    }

    public Recommendation(int id_Recommendation, String description) {
        idRecommendation = id_Recommendation;
        this.description = description;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public int getIdRecommendation() {
        return idRecommendation;
    }

    public void setIdRecommendation(int idRecommendation) {
        this.idRecommendation = idRecommendation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
