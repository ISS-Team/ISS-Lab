package cmsteam2.common.domain;

import javax.persistence.*;

@Entity
@Table(name = "Reviewer", uniqueConstraints = @UniqueConstraint(columnNames = {"Username", "id_ResearchPaper"}))
public class Reviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Reviewer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ResearchPaper")
    private ResearchPaper researchPaper;

    private StatusReviewer statusReviewer;

    public int getId_Reviewer() {
        return id_Reviewer;
    }

    public void setId_Reviewer(int id_Reviewer) {
        this.id_Reviewer = id_Reviewer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResearchPaper getResearchPaper() {
        return researchPaper;
    }

    public void setResearchPaper(ResearchPaper researchPaper) {
        this.researchPaper = researchPaper;
    }

    public StatusReviewer getStatusReviewer() {
        return statusReviewer;
    }

    public void setStatusReviewer(StatusReviewer statusReviewer) {
        this.statusReviewer = statusReviewer;
    }
}
