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

    private String username;
    private String password;

    protected Passwords_Checker pass;

    public LoginPassing() {
        this.pass = new Passwords_Checker();
    }

    public boolean selectionUsersNames(String username, String password) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                statement.setString(2, password);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.username = resultSet.getString(1);
                        System.out.println(username);
                        this.password = resultSet.getString(2);
                        System.out.println(password);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error creating database connection");
        return false;
    }


    public boolean selectionUsersCheck(String username) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username FROM UsersNames WHERE Username = ?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.username = resultSet.getString(1);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection");
            return false;
        }
        return false;
    }

}
