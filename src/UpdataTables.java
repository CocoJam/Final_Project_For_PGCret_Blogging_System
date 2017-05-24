import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class UpdataTables {

    private Connection conn;


    public void updataComments(int CommentId, int ArticlesID, String CommenterName, String Comments) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?,?);"
            );
            {   statement.setInt(1,CommentId);
                statement.setInt(2, ArticlesID);
                statement.setString(3, CommenterName);
                statement.setString(4, Comments);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataArticles(int ArticlesID, String ArticleName, String UserIDName, String content, Date SpecificDateCreated) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?,?,?);"
            );
            {
                statement.setInt(1, ArticlesID);
                statement.setString(2, ArticleName);
                statement.setString(3, UserIDName);
                statement.setString(4, content);
                statement.setDate( 5,SpecificDateCreated);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersProfile(String username, String email, String address, String education, String ethnicity) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile VALUES( ?, ? ,?,?,?);"
            );
            {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, address);
                statement.setString(4, education);
                statement.setString(5, ethnicity);
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
