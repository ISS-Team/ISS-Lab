package cmsteam2.test;

import cmsteam2.common.domain.Qualifier;
import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.Review;
import cmsteam2.common.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class ReviewTest {

    @Test
    public void createReview() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Review r = new Review();
        r.setDate(new Date(1000000));
        r.setQualifier(Qualifier.ACCEPT);
        r.setReviewer(u);
        r.setReviewedPaper(p);
        Assert.assertEquals(r.getReviewedPaper().getId(), 1);
        Assert.assertEquals(r.getQualifier(), Qualifier.ACCEPT);
    }

    @Test
    public void changeReview() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        User u2 = new User("User2", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Review r = new Review();
        r.setDate(new Date(1000000));
        r.setQualifier(Qualifier.ACCEPT);
        r.setReviewer(u);
        r.setReviewedPaper(p);
        Assert.assertEquals(r.getReviewer().getUsername(), "User");
        r.setReviewer(u2);
        Assert.assertEquals(r.getReviewer().getUsername(), "User2");
    }


}
