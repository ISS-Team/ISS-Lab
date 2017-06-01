package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSession;
    private String title;
    private long startTime;
    private long duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Conference")
    private Conference conference;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private Set<Participant> participanti = new HashSet<>();

    public Session(String title, long startTime, long duration) {
        this.duration = duration;
        this.startTime = startTime;
        this.title = title;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
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

    public Set<Participant> getParticipanti() {
        return participanti;
    }

    public void setParticipanti(Set<Participant> participanti) {
        this.participanti = participanti;
    }
}
