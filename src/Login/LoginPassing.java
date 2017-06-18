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

    
    // Using sql to check login and check for one of the part for deleting profile
    public synchronized boolean selectionUsersNames(String username, String password) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;")) {
                getSaltAndIteration(username);
                statement.setString(1, username);
                statement.setString(2, pass.hashing(password, salt, iteration));
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.username = resultSet.getString(1);
                        return true;
                    }
                }
                
            }
            
        } catch (SQLException e) {
            
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

// Using for ajax as the username check is it available 
    public synchronized boolean selectionUsersCheck(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username FROM UsersNames WHERE Username = ?;")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.username = resultSet.getString(1);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return false;
    }

    //Getting the salt and iteration values based on username for hasing the given password to match
    public synchronized void getSaltAndIteration(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT salt,iteration FROM UsersNames WHERE Username = ?;")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        salt = resultSet.getInt(1);
                        iteration = resultSet.getInt(2);
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
