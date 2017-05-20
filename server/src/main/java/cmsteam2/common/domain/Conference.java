package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Costi on 07.05.2017.
 */
/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name = "Conference")
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String theme;
    private Date date;
    private Date deadline;
    private long startTime;
    private long endTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference")
    private Set<Session> sessions=new HashSet<>();

    public Conference(String title, String theme, Date date,Date deadline) {
        this.deadline = deadline;
        this.title = title;
        this.theme = theme;
        this.date = date;
    }

    public void addSession(Session s) {
        sessions.add(s);
    }

    public Conference() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
