package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.SessionRepository;
import cmsteam2.common.domain.Session;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences/{conferenceId}/sessions")
public class SessionController {

    private SessionRepository sessionRepository = new SessionRepository(GenericRepository.loadProps());

    @GetMapping("/getall")
    public List<Session> getAll(@PathVariable int conferenceId) {
        return sessionRepository.getAllByConference(conferenceId);
    }
}
