package cmsteam2.backend;

import cmsteam2.common.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class UsersRepository extends GenericRepository<User> {
    public UsersRepository(Properties props, SessionFactory sessionFactory) {
        super(props);
    }

    public boolean usernameExists(String username) {
        boolean ok = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<User> users = session.createQuery("From User u where u.username like '" + username + "'", User.class).list();
            System.out.println(users.size());
            if (users.size() == 1) {
                ok = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return ok;
    }

    public String getPassword(String username) {
        String parola = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<User> users = session.createQuery("From User u where u.username like '" + username + "'", User.class).list();
            System.out.println(users.size());
            if (users.size() == 1) {
                parola = users.get(0).getPassword();
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return parola;
    }

    public User login(User user) {
        try (Session s = sessionFactory.openSession()) {
            Transaction tx = s.beginTransaction();
            User result = s.createQuery("from User U where U.username like '" + user.getUsername() + "' and U.password like '" + user.getPassword() + "'", User.class).uniqueResult();
            tx.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
