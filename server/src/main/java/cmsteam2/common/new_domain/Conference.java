package cmsteam2.common.new_domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String theme;
    private Date date;
    private Date deadlineAbstractInfo;
    private Date deadlineFullPaper;
    private Date deadlineReview;
    private Date startTime;
    private Date endTime;

    @OneToMany
    private Set<ResearchPaper> researchPapers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference")
    private Set<Session> sessions = new HashSet<>();

    public Conference() {}

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

    public int getId() {
        return id;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
