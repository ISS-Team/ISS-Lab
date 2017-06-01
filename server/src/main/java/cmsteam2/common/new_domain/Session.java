package cmsteam2.common.new_domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private long startTime;
    private long duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Conference")
    private Conference conference;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Set<User> participants = new HashSet<>();

    public Session(String title, long startTime, long duration) {
        this.duration = duration;
        this.startTime = startTime;
        this.title = title;
    }

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

    public Set<User> getParticipants() {
        return participants;
    }
}
