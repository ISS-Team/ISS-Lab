package cmsteam2.test;

import cmsteam2.common.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void createTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        Assert.assertEquals(u.getUsername(), "User");
        Assert.assertEquals(u.getPassword(), "Pass");
        Assert.assertEquals(u.getEmail(), "email@m.com");
        Assert.assertFalse(u.isActivated());
        Assert.assertEquals(u.getPermissionLevel(), 1);
    }

    @Test
    public void changeTest() {
        User u = new User("User", "Pass", "email@m.com", false, User.Permission.AUTHOR);
        Assert.assertEquals(u.getPermissionLevel(), 1);
        u.setPermissionLevel(User.Permission.CHAIR);
        Assert.assertEquals(u.getPermissionLevel(), 3);
    }
}
