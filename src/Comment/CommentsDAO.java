package Comment;

import Connection.*;

import javax.xml.stream.events.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class CommentsDAO {

//Select comments based on the comment Id for display
    public synchronized Comments selectionComment(int CommentID) throws NullPointerException {
        Comments comment = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommentID = ?;")) {
                statement.setInt(1, CommentID);
                comment = makeComment( statement);
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return comment;
    }

//Select a list of comments for a specific Article based on the ID
    public synchronized List<Comments> selectionComments(int articlesID) throws NullPointerException{
        List<Comments> listOfComments = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID, CommenterName, Comments, CommentTime FROM Comments WHERE ArticlesID = ?;")) {
                statement.setInt(1, articlesID);
                makeComment(listOfComments, statement);
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return listOfComments;
    }

//Usering to refact the Comment DAO to form Comments object and form a list
    private synchronized void makeComment(List<Comments> listOfComments, PreparedStatement statement) throws SQLException, NullPointerException {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Comments comments = new Comments();
                int CommentID = resultSet.getInt(1);
                int ArticleID = resultSet.getInt(2);
                String CommentName = resultSet.getString(3);
                String Comment = resultSet.getString(4);
                Date CommentTime = resultSet.getTimestamp(5);
                commentsSetStatments(comments, CommentID, ArticleID, CommentName, Comment, CommentTime);
                listOfComments.add(comments);
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //Used by other DAO to form Comments as an object
    private synchronized Comments makeComment( PreparedStatement statement) throws SQLException,NullPointerException {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Comments comments = new Comments();
                int CommentID = resultSet.getInt(1);
                int ArticleID = resultSet.getInt(2);
                String CommentName = resultSet.getString(3);
                String Comment = resultSet.getString(4);
                Date CommentTime = resultSet.getTimestamp(5);
                commentsSetStatments(comments, CommentID, ArticleID, CommentName, Comment, CommentTime);
                return comments;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return null;
    }

    //Comments setters from the sql to set into the Comment object
    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setActicleId(articleID);
        comments.setContent(comment);
        comments.setUsername(commentName);
        comments.setCommentId(commentID);
        comments.setCommentedTime(commentTime);
    }

//Inserting newly added Comments into the database
    public synchronized void AddingCommentsToDataBase(int ArticlesID, String CommenterName, String Comments) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES(?,?,?);")) {
                statement.setInt(1, ArticlesID);
                statement.setString(2, CommenterName);
                statement.setString(3, Comments);
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


    //Updating the Comments within the database from the given content and comment ID.
    public synchronized void editComments(String Comment, int CommentId) throws  NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Comments SET Comments = ? WHERE CommentID = ?;")) {
                statement.setString(1, Comment);
                statement.setInt(2, CommentId);
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

    //Used to respond the comment that is just added to the database from this user. And make sure the comment is select the last one.
    public synchronized Comments selectionLastComment(int articleId, String username, String comment)throws NullPointerException {
        Comments comments = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Comments WHERE ArticlesID=? AND CommenterName= ? AND Comments =? ORDER BY CommentID DESC Limit 1;")) {
                statement.setInt(1, articleId);
                statement.setString(2,username);
                statement.setString(3,comment);
                comments = makeComment(statement);
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return comments;
    }
}
