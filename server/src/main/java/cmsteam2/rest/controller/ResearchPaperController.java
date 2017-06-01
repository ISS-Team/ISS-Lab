package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ResearchPaperRepository;
import cmsteam2.common.new_domain.ResearchPaper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/conferences/{id}/papers")
@Transactional
public class ResearchPaperController {

    private ResearchPaperRepository researchPaperRepository;

    public ResearchPaperController() {
        researchPaperRepository = new ResearchPaperRepository(GenericRepository.loadProps());
    }

    private boolean checkPaper(ResearchPaper paper) {
        return (paper.getTopics().size() != 0 && paper.getKeywords().size() != 0 && !(paper.getAbstractPaper().equals("")));
    }

    @PostMapping
    @RequestMapping("/save")
    public ResponseEntity save(@RequestBody ResearchPaper paper) {
        if (checkPaper(paper)) {
            researchPaperRepository.save(paper);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody ResearchPaper paper) {
        if (checkPaper(paper))
            researchPaperRepository.update(paper);
            return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<ResearchPaper> getAll(@PathVariable int id) {
        return researchPaperRepository.getAll(id);
    }

    @GetMapping("/getaccepted")
    public List<ResearchPaper> getAllAccepted(@PathVariable int id) {
        List<ResearchPaper> papers = researchPaperRepository.getAll(id);
        papers.removeIf(paper -> !paper.isAccepted());
        return papers;
    }
}
