package cmsteam2.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Participation", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "id_Session"})})
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    @JsonIgnoreProperties({"biddings", "researchPapers", "password", "email", "sessions"})
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Session")
    @JsonIgnoreProperties({"conference", "paper", "participants"})
    private Session session;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Participation() {}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Status {
        SPEAKER,
        LISTENER,
        CHAIR;
    }
}