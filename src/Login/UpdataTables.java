package Login;
import Connection.*;
import java.sql.*;


/**
 * Created by ljam763 on 24/05/2017.
 */
public class UpdataTables {
    private Connection conn;
    private Passwords_Checker pass;

    public UpdataTables() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        pass = new Passwords_Checker();
    }

    public void updataComments(int ArticlesID, String CommenterName, String Comments) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES( ?, ? ,?);"
            );
            {
                statement.setInt(1, ArticlesID);
                statement.setString(2, CommenterName);
                statement.setString(3, Comments);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
