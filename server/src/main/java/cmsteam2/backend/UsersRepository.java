package cmsteam2.backend;

import cmsteam2.common.domain.User;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Costi on 15.05.2017.
 */
public class UsersRepository extends GenericRepository {


    public UsersRepository(Properties props) {
        super(props);
    }

    public void login(User user){
        Connection connection = getConnection();
/*        try {
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
        */
    }
}
