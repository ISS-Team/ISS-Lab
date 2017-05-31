package cmsteam2.backend;

import cmsteam2.common.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

/*    public String login(User user){
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from firmatransport.curse where username=?");
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idcursa");
                String destinatie = resultSet.getString("destinatie");
                Timestamp data_ora = resultSet.getTimestamp("data_ora");
                int locuriDisponibile = resultSet.getInt("locuri_disponibile");
                int oficiu = resultSet.getInt("oficiu");
                return new Cursa(id,destinatie,data_ora,locuriDisponibile,oficiu);
            }
            else {
                System.out.println("Nu exista in baza de date");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }*/
}
