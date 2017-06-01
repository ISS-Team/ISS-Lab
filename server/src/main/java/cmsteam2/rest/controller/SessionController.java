package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.SessionRepository;
import cmsteam2.common.new_domain.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/conferences/{id}/sessions")
public class SessionController {

    private SessionRepository sessionRepository = new SessionRepository(GenericRepository.loadProps());

    @GetMapping("/getAll")
    public List<Session> getAll(@RequestParam int conferenceId) {
        List<Session> sessions = sessionRepository.GetAllByConference(conferenceId);
        if (sessions != null) {
            return sessions;
        } else {
            return new ArrayList<Session>();
        }
    }


}
