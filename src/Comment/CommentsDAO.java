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


    public synchronized Comments selectionComment(int CommentID) throws NullPointerException {
        Comments comment = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommentID = ?;")) {
                statement.setInt(1, CommentID);
                comment = makeComment( statement);
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
        return comment;
    }


    public synchronized List<Comments> selectionComments(int articlesID) throws NullPointerException{
        List<Comments> listOfComments = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID, CommenterName, Comments, CommentTime FROM Comments WHERE ArticlesID = ?;")) {
                statement.setInt(1, articlesID);
                makeComment(listOfComments, statement);
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return listOfComments;
    }

    public synchronized List<Comments> selectionComments(String CommenterName) throws NullPointerException {
        List<Comments> listOfComments = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommenterName = ?;")) {
                statement.setString(1, CommenterName);
                makeComment(listOfComments, statement);
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
        return listOfComments;
    }

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
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

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
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return null;
    }

    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setActicleId(articleID);
        comments.setContent(comment);
        comments.setUsername(commentName);
        comments.setCommentId(commentID);
        comments.setCommentedTime(commentTime);
    }


    public synchronized void AddingCommentsToDataBase(int ArticlesID, String CommenterName, String Comments) throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES(?,?,?);")) {
                statement.setInt(1, ArticlesID);
                statement.setString(2, CommenterName);
                statement.setString(3, Comments);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized void editComments(String Comment, int CommentId) throws  NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Comments SET Comments = ? WHERE CommentID = ?;")) {
                statement.setString(1, Comment);
                statement.setInt(2, CommentId);
                statement.executeUpdate();
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Username already exist. Cannot create profile page.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

//    public Comments selectionLastComment() {
//        Comments comments = null;
//        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
//            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Comments ORDER BY CommentID DESC Limit 1;")) {
//                comments = makeComment(statement);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error. Comment not found");
//            e.printStackTrace();
//        }
//        return comments;
//    }

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
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return comments;
    }
}
