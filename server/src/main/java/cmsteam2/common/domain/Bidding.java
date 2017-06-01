package cmsteam2.common.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bidding")
@IdClass(Bidding.PK.class)
public class Bidding implements Serializable {

    public static class PK implements Serializable {
        private String user;

        public String getUser() {
            return user;
        }

        public int getPaper() {
            return paper;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setPaper(int paper) {
            this.paper = paper;
        }

        private int paper;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPaper(ResearchPaper paper) {
        this.paper = paper;
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="username", referencedColumnName = "username")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="paper_id", referencedColumnName = "id")
    private ResearchPaper paper;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Bidding() {}

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
        ACCEPTED,
        NEUTRAL,
        REJECTED;
    }

}
