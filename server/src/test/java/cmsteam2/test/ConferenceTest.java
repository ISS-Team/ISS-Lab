package cmsteam2.test;

import cmsteam2.common.domain.Conference;
import cmsteam2.common.domain.Session;
import org.junit.Assert;
import org.junit.Test;

public class ConferenceTest {

    @Test
    public void createTest() {
        Conference c = new Conference("Test", "Theme");
        Session s = new Session();
        s.setId(1);
        c.addSession(s);

        Assert.assertEquals(c.getTitle(), "Test");
        Assert.assertEquals(c.getTheme(), "Theme");
    }

    @Test
    public void changeTest() {
        Conference c = new Conference("Test", "Theme");
        Assert.assertEquals(c.getTitle(), "Test");
        Assert.assertEquals(c.getTheme(), "Theme");
        c.setTheme("Theme2");
        Assert.assertEquals(c.getTheme(), "Theme2");
    }

}
