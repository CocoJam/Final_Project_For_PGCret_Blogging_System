package Friend;


import Connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 8/06/2017.
 */
public class FriendDAO {
    //Selecting list of friends, based the username to populate a list of <friend>
    public synchronized List<Friend> selectionListOfFriends(String username) throws NullPointerException{
        List<Friend> ListOfFriends = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT friendusername FROM Friendlist WHERE username=?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Friend friend = new Friend();
                        String friendName = resultSet.getString(1);
                        friend.setFriendusername(friendName);
                        ListOfFriends.add(friend);
                        System.out.println(ListOfFriends.size());
                    }
                } catch (SQLException e) {
                    System.out.println("Error creating database connection.");
                    e.printStackTrace();
                }
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return ListOfFriends;
    }

    //To check and also add a friend.
    public synchronized boolean AddFriends(String username, String friendusername) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Friendlist (username, friendusername) VALUES(?,?);")) {
                System.out.println(statement);
                friendUpdateOrDelete(username, friendusername, statement);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
            return false;
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //Deleting friend from the database.
    public synchronized boolean DeleteFriends(String username, String friendusername) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Friendlist WHERE username = ? AND friendusername = ?;")) {
                System.out.println(statement);
                friendUpdateOrDelete(username, friendusername, statement);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
            return false;
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //Getting all Usernames into a String list
    public synchronized List<String> GetAllPeopleUsername() throws NullPointerException{
        List<String> usernamelist = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username FROM UsersNames;")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        usernamelist.add(resultSet.getString(1));
                    }
                } catch (SQLException e) {
                    System.out.println("Error creating database connection.");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        System.out.println(usernamelist.size());
        return usernamelist;
    }


    private synchronized void friendUpdateOrDelete(String username, String friendusername, PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, friendusername);
        statement.executeUpdate();
    }

}
