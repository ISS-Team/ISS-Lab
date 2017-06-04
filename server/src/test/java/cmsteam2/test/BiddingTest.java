package cmsteam2.test;

import cmsteam2.common.domain.Bidding;
import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class BiddingTest {

    @Test
    public void createTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Bidding b = new Bidding();
        b.setUser(u);
        b.setPaper(p);

        Assert.assertEquals(b.getPaper().getId(), 1);
        Assert.assertEquals(b.getUser().getUsername(), "User");
    }

    @Test
    public void changeTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Bidding b = new Bidding();
        b.setUser(u);
        b.setPaper(p);

        b.setStatus(Bidding.Status.ACCEPTED);
        Assert.assertEquals(b.getStatus(), Bidding.Status.ACCEPTED);
        b.setStatus(Bidding.Status.REJECTED);
        Assert.assertEquals(b.getStatus(), Bidding.Status.REJECTED);
    }

}
