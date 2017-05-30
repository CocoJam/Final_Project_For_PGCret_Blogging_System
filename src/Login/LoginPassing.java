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
    public Connection conn;
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
                System.out.println(statement);
                statement.setString(1, username);
                statement.setString(2, password);
                System.out.println(statement);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    System.out.println(username);
                    this.password = resultSet.getString(2);
                    System.out.println(password);

                }
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean selectionUsersCheck(String username) {
        try {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username FROM UsersNames WHERE Username = ?;"
            );
            {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                }
                return true;
            }
        } catch (SQLException e) {
                return false;
        }
    }

}
