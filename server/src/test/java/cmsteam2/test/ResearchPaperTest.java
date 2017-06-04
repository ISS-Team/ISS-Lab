package cmsteam2.test;

import cmsteam2.common.domain.ResearchPaper;
import cmsteam2.common.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class ResearchPaperTest {

    @Test
    public void createTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Assert.assertEquals(p.getId(), 1);
        Assert.assertEquals(p.getTitle(), "This is some test");
    }

    @Test
    public void changeTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        User u2 = new User("User2", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        ResearchPaper p = new ResearchPaper(1, "This is some test", u);
        Assert.assertEquals(p.getAuthor().getUsername(), "User");
        p.setAuthor(u2);
        Assert.assertEquals(p.getAuthor().getUsername(), "User2");
    }

}