package cmsteam2.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static cmsteam2.middleware.Main.sessionFactory;

public class GenericRepository <T> {

    private Properties props;

    public GenericRepository(Properties props) {
        this.props = props;
    }

    public Connection getConnection() {
        try {
            String driver = props.getProperty("jdbc.driver");
            String url = props.getProperty("jdbc.url");
            Class.forName(driver);
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T t) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void save(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    public static Properties loadProps() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("Bd.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
