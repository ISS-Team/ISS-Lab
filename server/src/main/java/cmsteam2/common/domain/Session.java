package cmsteam2.common.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Session")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private long startTime;
    private long duration;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Conference", referencedColumnName = "id")
    private Conference conference;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session", fetch = FetchType.EAGER)
    private Set<Participation> participants = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    private ResearchPaper paper;

    public Session() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<Participation> getParticipants() {
        return participants;
    }

    public ResearchPaper getPaper() {
        return paper;
    }
}
