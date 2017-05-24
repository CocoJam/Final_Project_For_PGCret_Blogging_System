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

    public void updataComments(int CommentId, int ArticlesID, String CommenterName, String Comments, Timestamp CommentTime) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile (ArticlesID, CommenterName, Comments, CommentTime) VALUES( ?, ? ,?,?,?);"
            );
            {   statement.setInt(1,CommentId);
                statement.setInt(2, ArticlesID);
                statement.setString(3, CommenterName);
                statement.setString(4, Comments);
                statement.setTimestamp(5, CommentTime);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataArticles(int ArticlesID, String ArticleName, String UserIDName, String content, Timestamp SpecificDateCreated) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile (ArticlesName, UserIDName, Content,SpecificDateCreated) VALUES( ? ,?,?,?);"
            );
            {
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, content);
                statement.setTimestamp( 4,SpecificDateCreated);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersProfile(String username, String email, String address, String education, String ethnicity,Date DateOfBirth) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO usersProfile (Username, Name, Email, Address, Education, Race) VALUES( ?, ? ,?,?,?,?);"
            );
            {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, address);
                statement.setString(4, education);
                statement.setString(5, ethnicity);
                statement.setDate(6,DateOfBirth);
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


    public static void main(String[] args) {
        //testing grounds here
       UpdataTables updataTables = new UpdataTables();
       //Date is year - month - date
       Date date = new Date(2016-05-30);
//        updataTables.updataUsersNames("ljam763", "blah");
        updataTables.updataUsersProfile("ljam763", "ljam763@gmail.com", "blah", null,"chinese", date);

    }
}
