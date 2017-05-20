package cmsteam2.common.domain;


import javax.persistence.*;
/**
 * Daca lipseste un getter, setter sau alta metoda care erau si le-am sters din greseala sau aveti nevoie va rog sa completati
 */
@Entity
@Table(name = "Participant",uniqueConstraints = {@UniqueConstraint(columnNames = {"username","id_session"})})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id_Participant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Session")
    private Session session;
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id_Review")
    private Review review;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId_Participant() {
        return id_Participant;
    }

    public void setId_Participant(int id_Participant) {
        this.id_Participant = id_Participant;
    }
}
