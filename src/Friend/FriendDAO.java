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
    public List<Friend> selectionListOfFriends(String username) {

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
        return ListOfFriends;
    }

    //To check and also add a friend.
    public boolean AddFriends(String username, String friendusername) {
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
        }
    }

    //Deleting friend from the database.
    public boolean DeleteFriends(String username, String friendusername) {
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
        }
    }

    //Getting all Usernames into a String list
    public List<String> GetAllPeopleUsername() {
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
        }
        System.out.println(usernamelist.size());
        return usernamelist;
    }


    private void friendUpdateOrDelete(String username, String friendusername, PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, friendusername);
        statement.executeUpdate();
    }

    public void pullAllFriendProfile(List<Friend> list) {
        // get list, populate into SQL
//        List<Friend> list = friendList;
        System.out.println("123123123");
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {

            System.out.println("76456546456");

            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, profilePicture FROM uoaslashn.UsersNames;")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        for (Friend friend : list) {
                            if (friend.getFriendusername().equals(resultSet.getString(1))) {
                                friend.setFriendProfilePicture(resultSet.getString(2));
                                System.out.println("Added profile picture to friend");
                                System.out.println(resultSet.getString(1));
                                System.out.println(resultSet.getString(2));
                            }
                        }

                    }
                } catch (SQLException e) {
                    System.out.println("Error creating database connection.");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
            return;
        }

//        for (Friend listOfFriend : ListOfFriends) {
//            listOfFriend.getFriendusername()
//        }
    }

}
