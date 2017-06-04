package cmsteam2.test;

import cmsteam2.common.domain.Participation;
import cmsteam2.common.domain.Session;
import cmsteam2.common.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class ParticipationTest {

    @Test
    public void createTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        Session s = new Session();
        s.setTitle("Hello there");
        s.setId(1);
        Participation p = new Participation();
        p.setStatus(Participation.Status.LISTENER);
        p.setUser(u);
        p.setSession(s);

        Assert.assertEquals(p.getSession().getTitle(), "Hello there");
        Assert.assertEquals(p.getUser().getUsername(), "User");
    }

}
