package cmsteam2.common.domain;

public class User {

    public final String name;
    public final String password;
    public final String email;
    public final boolean isActivated;

    public User(String name, String password, String email, boolean isActivated) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.isActivated = isActivated;
    }

}
