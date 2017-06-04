package cmsteam2.test;

import cmsteam2.common.domain.Session;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    @Test
    public void createTest() {
        Session s = new Session();
        s.setDuration(1000);
        s.setId(1);
        s.setPaper(null);
        s.setTitle("This is a test");
        Assert.assertEquals(s.getDuration(), 1000);
        Assert.assertEquals(s.getId(), 1);
        Assert.assertEquals(s.getTitle(), "This is a test");
    }

    @Test
    public void changeTest() {
        Session s = new Session();
        s.setDuration(1000);
        s.setId(1);
        s.setPaper(null);
        s.setTitle("This is a test");
        Assert.assertEquals(s.getTitle(), "This is a test");
        s.setTitle("This is another test");
        Assert.assertEquals(s.getTitle(), "This is another test");
    }

}
