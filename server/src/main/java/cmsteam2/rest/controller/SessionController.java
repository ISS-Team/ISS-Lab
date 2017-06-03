package cmsteam2.rest.controller;

import cmsteam2.backend.ConferenceRepository;
import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ResearchPaperRepository;
import cmsteam2.backend.SessionRepository;
import cmsteam2.common.domain.Conference;
import cmsteam2.common.domain.Participation;
import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.Session;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static cmsteam2.middleware.Main.sessionFactory;

@RestController
@RequestMapping("/conferences/{conferenceId}/sessions")
public class SessionController {

    public static SessionController instance;

    private SessionRepository sessionRepository = new SessionRepository(GenericRepository.loadProps());
    private ResearchPaperRepository researchPaperRepository=new ResearchPaperRepository(GenericRepository.loadProps());
    private ConferenceRepository conferenceRepository=new ConferenceRepository(GenericRepository.loadProps());

    public SessionController() {
        instance = this;
    }

    @GetMapping("/getall")
    public List<Session> getAll(@PathVariable int conferenceId) {
        return sessionRepository.getAllByConference(conferenceId);
    }

    @GetMapping("/participate/{sessionId}")
    public void participate(@PathVariable int sessionId, @SessionAttribute("username") String username) {
        sessionRepository.participate(sessionId, username, Participation.Status.LISTENER);
    }


    /**
     * Version 1 Sessions has a fix number of minute(30m)--implemented
     * Version 2 Sessions has a variable number of minutes(minimum 15m)--implemented
     * Version 3 Sessions has a variable number of minutes based of grade given(grade>2 :45m, 2>grade>1: 30m, 1>grade>0 15 m)--to be implemented if needed
     */

    @GetMapping("/version1")
    public void makeSessionsVersion1(@PathVariable int conferenceId) throws ParseException {
        List<ResearchPaper> researchPapers=researchPaperRepository.getAll(conferenceId);
        Conference conference=conferenceRepository.getById(conferenceId);
//        Time time=Time.valueOf("00:00:00");
//        conference.setStartTime(time);
//        conference.setEndTime(Time.valueOf("1:00:00"));
//        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        java.util.Date testDate=dateFormat.parse("2017-10-12 12:00:00");
//        java.util.Date endDate=dateFormat.parse("2017-10-12 18:00:00");
//        System.out.println(testDate);
//        System.out.println(testDate.toString());
//        conference.setStartTime(testDate);
//        conference.setEndTime(endDate);
//        org.hibernate.Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.update(conference);
//        session.getTransaction().commit();
//        session.close();

        researchPapers.removeIf(researchPaper -> !researchPaper.isAccepted());

        long minDizpozitie=timeInMinuteOfConference(conference.getStartTime(),conference.getEndTime());
        researchPapers.sort(Comparator.comparingInt(u -> u.researchPaperWeight()));
        long startTime=conference.getStartTime().getTime();
        long copyStartTime=startTime;
        long endTime=conference.getEndTime().getTime();

        for(ResearchPaper researchPaper:researchPapers){
            Session session=new Session();
            session.setConference(conference);
            session.setDuration(60*1000*30);//30 minute
            session.setStartTime(startTime);
            session.setTitle(researchPaper.getTitle());
            sessionRepository.save(session);
            startTime = startTime + 60 * 1000 * 30;//+30 min;
            if(startTime>=(endTime-60 * 1000 *30+1))//to not pass the entTime(not even with a ms)
                startTime=copyStartTime;
        }

    }

    @GetMapping("/version2")
    public void makeSessionsVersion2(@PathVariable int conferenceId) throws ParseException {
        List<ResearchPaper> researchPapers=researchPaperRepository.getAll(conferenceId);
        Conference conference=conferenceRepository.getById(conferenceId);
//        Time time=Time.valueOf("00:00:00");
//        conference.setStartTime(time);
//        conference.setEndTime(Time.valueOf("0:30:00"));
        researchPapers.removeIf(researchPaper -> !researchPaper.isAccepted());

        long totalMinutes=timeInMinuteOfConference(conference.getStartTime(),conference.getEndTime());
        int interval;//the duration for a session
        if(totalMinutes/researchPapers.size()>=15)//setting the duration
            interval=(int)totalMinutes/researchPapers.size();
        else
            interval=15;

        researchPapers.sort(Comparator.comparingInt(u -> u.researchPaperWeight()));
        long startTime=conference.getStartTime().getTime();
        long copyStartTime=startTime;
        long endTime=conference.getEndTime().getTime();
        for(ResearchPaper researchPaper:researchPapers){
            Session session=new Session();
            session.setConference(conference);
            session.setDuration(60*1000*interval);
            session.setStartTime(startTime);
            session.setTitle(researchPaper.getTitle());
            session.setPaper(researchPaper);

            sessionRepository.save(session);
            startTime = startTime + 60 * 1000 * interval;
            if(startTime>=(endTime-60 * 1000 *interval+1))//to not pass the entTime
                startTime=copyStartTime;
        }

    }
    public long timeInMinuteOfConference(java.util.Date start, java.util.Date end){
        long diff=end.getTime()-start.getTime();
        diff=diff/(1000*60);
        System.out.println(diff);
        return diff;
    }
}
