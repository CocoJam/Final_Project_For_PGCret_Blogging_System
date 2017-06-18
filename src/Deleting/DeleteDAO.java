package Deleting;

import Connection.ConnectionToTheDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeleteDAO {

    public DeleteDAO() {
        super();
    }


    public synchronized void dropSpeificComment(int CommentID) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE CommentID = ?;")) {
                
                
                statement.setInt(1, CommentID);
                
                statement.executeUpdate();
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }


    public synchronized void dropSpeificArticle(int ArticlesID) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try {
                dropArticleAllComments(ArticlesID);
                try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Articles WHERE ArticlesID = ?;")) {
                    
                    statement.setInt(1, ArticlesID);
                    
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void dropArticleAllComments(int ArticlesID) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE ArticlesID = ?;")) {
                
                statement.setInt(1, ArticlesID);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void dropComments(String username) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE CommenterName = ?;")) {
                
                statement.setString(1, username);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public void dropArticles(String username) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Articles WHERE UserIDName = ?;")) {
                statement.setString(1, username);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void dropUserInformation(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM UsersNames WHERE Username = ?;")) {
                statement.setString(1, username);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

//    public synchronized void dropSpeificYoutube(String youtubeURL) throws NullPointerException{
//        try (Connection connection = new ConnectionToTheDataBase().getConn()){
//            PreparedStatement statement = connection.prepareStatement(
//                    "DELETE FROM youtube WHERE youtubeURL LIKE '%'?'%';"
//            );
//            statement.setString(1, youtubeURL);
//            
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//            throw new NullPointerException();
//        }
//    }

    public synchronized void dropLikes(int ArticleId) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM likes WHERE ArticlesID = ?;"
            );
            statement.setInt(1, ArticleId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void dropLikes(String usernames) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM likes WHERE username = ?;"
            );
            statement.setString(1, usernames);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void DeleteFriendsseondround(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Friendlist WHERE friendusername = ? ")) {
                statement.setString(1, username);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void DeleteFriends(String username) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Friendlist WHERE username = ? ")) {
                
                statement.setString(1, username);
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void dropAllByUsername(String username) {
        DeleteFriends( username);
        DeleteFriendsseondround( username);
        dropLikes(username);
        dropComments(username);
        dropArticles(username);
        dropUserInformation(username);

    }
    public synchronized void dropSpecificArticlesAll(int ArticleId){
        dropLikes(ArticleId);
        dropArticleAllComments(ArticleId);
        dropSpeificArticle(ArticleId);
    }
}
