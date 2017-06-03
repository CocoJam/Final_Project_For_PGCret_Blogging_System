package Login;

import Connection.*;

import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */

//This is superclass of all DAO's to access the connection and Config details.
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
    private int salt;
    private int iteration;
    public LoginPassing() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        this.pass = new Passwords_Checker();
    }

    public boolean selectionUsersNames(String username, String password) {
        try {
            getSaltAndIteration(username);
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;"
            );
            {
                System.out.println(statement);
                System.out.println(salt);
                System.out.println(iteration);
                statement.setString(1, username);
                statement.setString(2, pass.hashing(password,salt,iteration));
                System.out.println(statement);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    System.out.println(username);
                    return true;
                }

            }
        } catch (SQLException e) {
            System.out.println("Error login");
            return false;
        }
        catch (IllegalArgumentException e){
            return false;
        }
        return false;
    }


    public  void getSaltAndIteration(String username) {
        try {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT salt,iteration FROM UsersNames WHERE Username = ?;"
            );
            {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                   salt = resultSet.getInt(1);
                   iteration = resultSet.getInt(2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                    return true;
                }

            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

}
