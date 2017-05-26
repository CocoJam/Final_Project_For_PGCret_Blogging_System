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

    public void updataArticles( String ArticleName, String UserIDName, String content, Timestamp SpecificDateCreated) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Articles (ArticlesName, UserIDName, Content) VALUES( ? ,?,?);"
            );
            {
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, content);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersProfile(String username, String name, String email, String address, String education, String ethnicity,Date DateOfBirth) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO UsersNames (Username, Name, Email, Address, Education, Ethnicity , DateOfBirth) VALUES( ?, ? ,?,?,?,?,?);"
            );
            {
                statement.setString(1, username);
                statement.setString(2, name);
                statement.setString(3, email);
                statement.setString(4, address);
                statement.setString(5, education);
                statement.setString(6, ethnicity);
                statement.setDate(7,DateOfBirth);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersNames(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO UsersNames (Username, Password) VALUES( ?, ? );"
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
        updataTables.updataUsersNames("ljam763", "blah");
//        updataTables.updataUsersProfile("ljam763","James", "ljam763@gmail.com", "blah", null,"chinese", date);

    }
}
