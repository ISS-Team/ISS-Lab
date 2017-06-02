package cmsteam2.common.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private boolean isActivated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ResearchPaper> researchPapers = new HashSet<>();

    @OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
    private Set<Bidding> biddings = new HashSet<>();

    /**
     * Permission level. The higher the more permission the user has.
     */
    private int permissionLevel;

    public User() {}

    public User(String username, String password, String email, boolean isActivated, Permission permission) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Bidding> getBiddings() {
        return biddings;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public Set<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public int getPermissionLevel() {
        return permissionLevel;
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
