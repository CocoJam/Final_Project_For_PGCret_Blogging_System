import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class UpdataTables {

    private Connection conn;


    public void updataComments(int ArticlesID, String CommenterName, String Comments) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?);"
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

    public void updataArticles(int ArticlesID, String ArticleName, String UserIDName, String content) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?,?);"
            );
            {
                statement.setInt(1, ArticlesID);
                statement.setString(2, ArticleName);
                statement.setString(3, UserIDName);
                statement.setString(4, content);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersProfile(String username, String email, String address, String education, String race) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?,?,?);"
            );
            {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, address);
                statement.setString(4, education);
                statement.setString(5, race);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersNames(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO UsersNames VALUES( ?, ? );"
            );
            {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
