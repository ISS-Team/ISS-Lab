package cmsteam2.backend;

import cmsteam2.common.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class UsersRepository extends GenericRepository {
    public UsersRepository(Properties props, SessionFactory sessionFactory) {
        super(props);
    }

    public boolean checkUsername(String username) {
        org.hibernate.Session session = sessionFactory.openSession();
        boolean ok = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<User> useri = session.createQuery("From User u where u.username like '" + username + "'", User.class).list();
            System.out.println(useri.size());
            if (useri.size() == 1)
                ok = true;
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
            return ok;
        }
    }

    public String getPassword(String username) {
        org.hibernate.Session session = sessionFactory.openSession();
        boolean ok = false;
        String parola = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<User> useri = session.createQuery("From User u where u.username like '" + username + "'", User.class).list();
            System.out.println(useri.size());
            if (useri.size() == 1)
                parola = useri.get(0).getPassword();
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
            return parola;
        }
    }


    public void save(User user) {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
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
