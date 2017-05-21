package cmsteam2.common.domain;

import cmsteam2.common.domain.Participant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private boolean isActivated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reviewer> Reviewers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ResearchPaper> researchPapers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Participant> sesiuni = new HashSet<>();




    /**
     * Permission level. The higher the more permission the user has.
     */
    private int permissionLevel;

    public User() {
    }

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

    public Set<Participant> getSesiuni() {
        return sesiuni;
    }

    public void setSesiuni(Set<Participant> sesiuni) {
        this.sesiuni = sesiuni;
    }
    public void addResearchPaper(ResearchPaper researchPaper){
        researchPapers.add(researchPaper);
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
