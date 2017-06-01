package cmsteam2.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Bidding")
public class Bidding {

    private User user;
    private ResearchPaper paper;
    private Status status;

    public enum Status {
        ACCEPTED,
        NEUTRAL,
        REJECTED;
    }

}
