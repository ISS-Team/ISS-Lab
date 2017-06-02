package cmsteam2.rest.controller;

import cmsteam2.backend.ConferenceRepository;
import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ResearchPaperRepository;
import cmsteam2.backend.SessionRepository;
import cmsteam2.common.domain.Conference;
import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.Session;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/conferences/{conferenceId}/sessions")
public class SessionController {

    private SessionRepository sessionRepository = new SessionRepository(GenericRepository.loadProps());
    private ResearchPaperRepository researchPaperRepository=new ResearchPaperRepository(GenericRepository.loadProps());
    private ConferenceRepository conferenceRepository=new ConferenceRepository(GenericRepository.loadProps());
    @GetMapping("/getall")
    public List<Session> getAll(@PathVariable int conferenceId) {
        return sessionRepository.getAllByConference(conferenceId);
    }


    /**
     * Version 1 Sessions has a fix number of minute(30m)--implemented
     * Version 2 Sessions has a variable number of minutes(minimum 15m)--implemented
     * Version 3 Sessions has a variable number of minutes based of grade given(grade>2 :45m, 2>grade>1: 30m, 1>grade>0 15 m)--to be implemented if needed
     */

    @GetMapping("/version1")
    public void makeSessionsVersion1(@PathVariable int conferenceId) {
        List<ResearchPaper> researchPapers=researchPaperRepository.getAll(conferenceId);
        Conference conference=conferenceRepository.getById(conferenceId);
//        Time time=Time.valueOf("00:00:00");
//        conference.setStartTime(time);
//        conference.setEndTime(Time.valueOf("1:00:00"));
        for (ResearchPaper researchPaper:researchPapers) {
            if(!researchPaper.isAccepted())
                researchPapers.remove(researchPaper);

        }
        long minDizpozitie=timeInMinuteOfConference(conference.getStartTime(),conference.getEndTime());
//        int pararel=1;
//        int cate=0;
//        if(minDizpozitie/30<researchPapers.size()){
//            System.out.println((int)Math.ceil(researchPapers.size()/(minDizpozitie/30)));
//            System.out.println(researchPapers.size()/(minDizpozitie/30));
//            pararel=(int)Math.ceil(researchPapers.size()/(minDizpozitie/30))+1;
//            cate=(int)(researchPapers.size()-minDizpozitie/30);
//        }
        researchPapers.sort(Comparator.comparingInt(u -> u.researchPaperWeight()));
        long startTime=conference.getStartTime().getTime();
        long copyStartTime=startTime;
        long endTime=conference.getEndTime().getTime();

//        int contorPararel=1;
//        int contorCate=0;
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

//            if(pararel!=1){
//                if(contorCate<cate){
//                    if(contorPararel<pararel)
//                    {
//                        contorPararel++;
//                    }
//                    else {
//                        contorPararel=1;
//                        contorCate++;
//                        startTime=startTime+60*1000*30;//+30 de min;
//                    }
//                }
//                else {
//                    startTime = startTime + 60 * 1000 * 30;//+30 de min;
//                }
//
//            }
//            else
//            {
//                startTime = startTime + 60 * 1000 * 30;//+30 de min;
//            }
        }

    }
    @GetMapping("/version2")
    public void makeSessionsVersion2(@PathVariable int conferenceId) throws ParseException {
        List<ResearchPaper> researchPapers=researchPaperRepository.getAll(conferenceId);
        Conference conference=conferenceRepository.getById(conferenceId);
//        Time time=Time.valueOf("00:00:00");
//        conference.setStartTime(time);
//        conference.setEndTime(Time.valueOf("0:30:00"));
        for (ResearchPaper researchPaper:researchPapers) {
            if(!researchPaper.isAccepted())
                researchPapers.remove(researchPaper);

        }
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
            sessionRepository.save(session);
            startTime = startTime + 60 * 1000 * interval;
            if(startTime>=(endTime-60 * 1000 *interval+1))//to not pass the entTime
                startTime=copyStartTime;
        }

    }
    public long timeInMinuteOfConference(Time start,Time end){
        long diff=end.getTime()-start.getTime();
        diff=diff/(1000*60);
        return diff;
    }
}
