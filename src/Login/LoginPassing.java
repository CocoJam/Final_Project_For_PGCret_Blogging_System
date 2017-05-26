package Login;
import Connection.*;
import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class LoginPassing {


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConn() {
        return conn;
    }

    private String username;
    private String password;
    protected Connection conn;
    protected Passwords_Checker pass;

    public LoginPassing() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        this.pass = new Passwords_Checker();
    }

    public boolean selectionUsersNames(String username, String password) {
        try {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;"
            );
            {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    System.out.println(username);
                    this.password = resultSet.getString(2);
                    System.out.println(password);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        //testing grounds here
        LoginPassing loginPassing = new LoginPassing();
        loginPassing.selectionUsersNames("ljam763", "blah");
        System.out.println( loginPassing.getUsername());
    }
}
