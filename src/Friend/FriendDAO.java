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
                
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Friend friend = new Friend();
                        String friendName = resultSet.getString(1);
                        friend.setFriendusername(friendName);
                        ListOfFriends.add(friend);
                        
                    }
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            }
            
        } catch (SQLException e) {
            
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
                
                friendUpdateOrDelete(username, friendusername, statement);
                return true;
            }
        } catch (SQLException e) {
            
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
                
                friendUpdateOrDelete(username, friendusername, statement);
                return true;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
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
                    
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        
        return usernamelist;
    }


    private synchronized void friendUpdateOrDelete(String username, String friendusername, PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, friendusername);
        statement.executeUpdate();
    }

    public void pullAllFriendProfile(List<Friend> list) {
        // get list, populate into SQL
//        List<Friend> list = friendList;
        
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {

            

            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, profilePicture FROM uoaslashn.UsersNames;")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        for (Friend friend : list) {
                            if (friend.getFriendusername().equals(resultSet.getString(1))) {
                                friend.setFriendProfilePicture(resultSet.getString(2));
                                
                                
                                
                            }
                        }

                    }
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return;
        }

//        for (Friend listOfFriend : ListOfFriends) {
//            listOfFriend.getFriendusername()
//        }
    }

}
