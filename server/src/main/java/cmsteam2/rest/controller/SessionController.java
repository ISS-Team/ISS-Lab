package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.SessionRepository;
import cmsteam2.common.domain.Session;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 5/28/2017.
 */


@RestController
@RequestMapping("/conferences/{id}/sessions")
public class SessionController {

    private SessionRepository sessionRepository = new SessionRepository(GenericRepository.loadProps());




    @GetMapping("/getAll")
    public List<Session> getAll(@RequestParam int conferenceId){
        List<Session> sessions = sessionRepository.GetAllByConference(conferenceId);
        if(sessions!=null){
            return sessions;
        }
        else{
            return new ArrayList<Session>();
        }
    }





}
