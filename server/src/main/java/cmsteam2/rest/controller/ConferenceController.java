package cmsteam2.rest.controller;

import cmsteam2.backend.ConferenceRepository;
import cmsteam2.backend.GenericRepository;
import cmsteam2.common.domain.Conference;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getall")
    public List<Conference> getAll() {
        return conferenceRepository.getAll();
    }
}
