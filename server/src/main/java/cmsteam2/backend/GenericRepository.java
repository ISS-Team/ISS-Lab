package cmsteam2.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 15/05/2017.
 */
public class GenericRepository {
    private Properties props;

    public GenericRepository(Properties props) {
        this.props = props;
    }

    public Connection getConnection(){
        Connection conn=null;
        try {
            String driver=props.getProperty("jdbc.driver");
            String url=props.getProperty("jdbc.url");
            Class.forName(driver);
            conn= DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            return conn;
        }

    }
}
