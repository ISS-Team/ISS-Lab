package cmsteam2.common.new_domain;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private boolean isActivated;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ResearchPaper> researchPapers = new HashSet<>();

    /**
     * Permission level. The higher the more permission the user has.
     */
    private int permissionLevel;

    public User() {}

    public User(String name, String password, String email, boolean isActivated, Permission permission) {
        this.username = name;
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

    public void addResearchPaper(ResearchPaper researchPaper) {
        researchPapers.add(researchPaper);
    }

    public String getPassword() {
        return password;
    }

    @JsonValue
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActivated=" + isActivated +
                ", permissionLevel=" + permissionLevel +
                '}';
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
