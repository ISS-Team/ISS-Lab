package cmsteam2.common.domain;

import java.util.ArrayList;

/**
 * Created by Costi on 07.05.2017.
 */
public class Session {
    private String id;
    private String title;
    private long startTime;
    private long duration;
    private ArrayList<User> speakers;
    private User sessionChair;

    public Session(String title,User speaker) {
        speakers=new ArrayList<User>();
        speakers.add(speaker);
        this.title = title;
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

    public ArrayList<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(ArrayList<User> speakers) {
        this.speakers = speakers;
    }

    public User getSessionChair() {
        return sessionChair;
    }

    public void setSessionChair(User sessionChair) {
        this.sessionChair = sessionChair;
    }
}
