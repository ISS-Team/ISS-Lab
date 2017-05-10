package cmsteam2.common.domain;

public class User {

    public final String name;
    public final String password;
    public final String email;
    public final boolean isActivated;

    /**
     * Permission level. The higher the more permission the user has.
     */
    private int permissionLevel;

    public User(String name, String password, String email, boolean isActivated, Permission permission) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.isActivated = isActivated;
        this.permissionLevel = permission.level;
    }

    public boolean hasPermission(Permission perm) {
        return perm.level <= permissionLevel;
    }

    public void setPermissionLevel(Permission perm) {
        this.permissionLevel = perm.level;
    }

    public enum Permission {
        LISTENER(0),
        AUTHOR(1),
        MEMBER(2),
        CHAIR(3);

        private int level;

        Permission(int level) {
            this.level = level;
        }
    }
}
