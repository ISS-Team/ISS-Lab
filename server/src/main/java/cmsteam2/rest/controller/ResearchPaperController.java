package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.ResearchPaperRepository;
import cmsteam2.common.domain.ResearchPaper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/researchPapers")
public class ResearchPaperController {

    private ResearchPaperRepository researchPaperRepository;

    public ResearchPaperController() {
        researchPaperRepository = new ResearchPaperRepository(GenericRepository.loadProps());
    }

    private boolean checkPaper(ResearchPaper paper) {
        return ((paper.getMetaData() != null) && !(paper.getAbstractPaper().equals("")));
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
            if (researchPaperRepository.update(paper))
                return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getResearchPaper")
    public List<ResearchPaper> getResearchPapers() {
        List<ResearchPaper> papers = researchPaperRepository.getAll();
        if (papers != null) {
            return papers;
        } else {
            return new ArrayList<ResearchPaper>();
        }
    }


}
