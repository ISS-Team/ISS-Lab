package cmsteam2.backend;

import cmsteam2.common.domain.Session;
import cmsteam2.common.domain.Conference;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ConferenceRepository extends GenericRepository {

    public ConferenceRepository(Properties props) {
        super(props);
    }

    public boolean update(Conference conference) {
        return false;
    }

    public void save(Conference conference) {

    }
}
