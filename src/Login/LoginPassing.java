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
    private int salt;
    private int iteration;

    protected Passwords_Checker pass;

    public LoginPassing() {
        this.pass = new Passwords_Checker();
    }

    public synchronized boolean selectionUsersNames(String username, String password) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;")) {
                getSaltAndIteration(username);
                statement.setString(1, username);
                statement.setString(2, pass.hashing(password, salt, iteration));
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.username = resultSet.getString(1);
                        return true;
                    }
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return false;
    }


    public synchronized boolean selectionUsersCheck(String username) throws NullPointerException {
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
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return false;
    }

    public synchronized void getSaltAndIteration(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT salt,iteration FROM UsersNames WHERE Username = ?;")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        salt = resultSet.getInt(1);
                        iteration = resultSet.getInt(2);
                        System.out.println(salt);
                        System.out.println(iteration);
                    }
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


}
