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
    private int id;
    private String title;
    private String theme;
    private Date date;
    private Date deadlineAbstractInfo;
    private Date deadlineFullPaper;
    private Date deadlineReview;
    private long startTime;
    private long endTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference")
    private Set<Session> sessions=new HashSet<>();

    public Conference(String title, String theme, Date date) {
        this.title = title;
        this.theme = theme;
        this.date = date;
    }

    public Conference(String title, String theme, Date date, Date deadlineAbstractInfo, Date deadlineFullPaper, Date deadlineReview) {
        this.title = title;
        this.theme = theme;
        this.date = date;
        this.deadlineAbstractInfo = deadlineAbstractInfo;
        this.deadlineFullPaper = deadlineFullPaper;
        this.deadlineReview = deadlineReview;
    }

    public void addSession(Session s) {
        sessions.add(s);
    }

    public Conference() {
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

    public Date getDeadlineAbstractInfo() {
        return deadlineAbstractInfo;
    }

    public void setDeadlineAbstractInfo(Date deadlineAbstractInfo) {
        this.deadlineAbstractInfo = deadlineAbstractInfo;
    }

    public Date getDeadlineFullPaper() {
        return deadlineFullPaper;
    }

    public void setDeadlineFullPaper(Date deadlineFullPaper) {
        this.deadlineFullPaper = deadlineFullPaper;
    }

    public Date getDeadlineReview() {
        return deadlineReview;
    }

    public void setDeadlineReview(Date deadlineReview) {
        this.deadlineReview = deadlineReview;
    }
}
