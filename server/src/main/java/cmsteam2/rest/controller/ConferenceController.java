package cmsteam2.rest.controller;

import cmsteam2.backend.ConferenceRepository;
import cmsteam2.backend.GenericRepository;
import cmsteam2.common.domain.Conference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    private ConferenceRepository conferenceRepository = new ConferenceRepository(GenericRepository.loadProps());

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Conference conference) {
        conferenceRepository.save(conference);
        return ResponseEntity.ok("{\"id\": " + conference.getId() + "}");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Conference conference) {
        conferenceRepository.update(conference);
        return ResponseEntity.ok("{}");
    }

    @GetMapping("/getall")
    public List<Conference> getAll() {
        return conferenceRepository.getAll();
    }
}
