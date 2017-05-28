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


    private final SessionFactory sessionFactory;

    public ConferenceRepository(Properties props, SessionFactory sessionFactory) {
        super(props);
        this.sessionFactory=sessionFactory;
    }

    public void  update (Date deadline){
    }

    public void save(Conference conference){

    }
}
