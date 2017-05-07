package cmsteam2.common.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Costi on 07.05.2017.
 */
public class Conference {
    private String id;
    private String title;
    private String theme;
    private Date date;
    private ArrayList<Date> deadlines;
    private long startTime;
    private long endTime;
    private ArrayList<Session> sessions;

    public Conference(String title, String theme, Date date) {
        deadlines=new ArrayList<>();
        sessions=new ArrayList<>();
        this.title = title;
        this.theme = theme;
        this.date = date;
    }

    public void addSession(Session s){
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

    public ArrayList<Date> getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(ArrayList<Date> deadlines) {
        this.deadlines = deadlines;
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
}
