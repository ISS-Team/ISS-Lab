package cmsteam2.rest.controller;

import cmsteam2.backend.ConferenceRepository;
import cmsteam2.common.domain.Conference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    private ConferenceRepository conferenceRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Conference conference) {
        conferenceRepository.save(conference);
        return ResponseEntity.status(HttpStatus.OK).body("{\"id\": " + conference.getId() + "}");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Conference conference) {
        boolean exists = conferenceRepository.update(conference);
        if (exists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
