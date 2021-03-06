package cmsteam2.common.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bidding")
public class Bidding implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="username", referencedColumnName = "username")
    @JsonIgnoreProperties({"biddings", "researchPapers", "password", "email"})
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="paper_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"bidders", "keywords", "topics", "conference", "reviews"})
    private ResearchPaper paper;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Bidding() {}

    public void setUser(User user) {
        this.user = user;
    }

    public void setPaper(ResearchPaper paper) {
        this.paper = paper;
    }

    public User getUser() {
        return user;
    }

    public ResearchPaper getPaper() {
        return paper;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ACCEPTED(1),
        NEUTRAL(0),
        REJECTED(-1);

        public int weight;

        Status(int weight) {
            this.weight = weight;
        }
    }

}
