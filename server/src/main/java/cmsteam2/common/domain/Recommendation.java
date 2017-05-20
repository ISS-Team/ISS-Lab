package cmsteam2.common.domain;

import javax.persistence.*;

/**
 * Created by alex on 5/10/2017.
 */
/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name= "Recommendation")
public class Recommendation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Recommendation;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Review",referencedColumnName = "Id_Review")
    private Review review;

    public Recommendation() {
    }

    public Recommendation(String description, Review review) {
        this.description = description;
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Recommendation(int id_Recommendation, String description) {
        Id_Recommendation = id_Recommendation;
        this.description = description;
    }

    public int getId_Recommendation() {
        return Id_Recommendation;
    }

    public void setId_Recommendation(int id_Recommendation) {
        Id_Recommendation = id_Recommendation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
