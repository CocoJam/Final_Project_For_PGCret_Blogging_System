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


    public Comments selectionComment(int CommentID) {
        Comments comment = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommentID = ?;")) {
                System.out.println(statement);
                statement.setInt(1, CommentID);
                comment = makeComment( statement);
                System.out.println(statement);
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        }
        return comment;
    }


    public List<Comments> selectionComments(int articlesID) {
        List<Comments> listOfComments = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID, CommenterName, Comments, CommentTime FROM Comments WHERE ArticlesID = ?;")) {
                System.out.println(statement);
                statement.setInt(1, articlesID);
                makeComment(listOfComments, statement);
                System.out.println(statement);
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found.");
            e.printStackTrace();
        }
        return listOfComments;
    }

    public List<Comments> selectionComments(String CommenterName) {
        List<Comments> listOfComments = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommenterName = ?;")) {
                System.out.println(statement);
                statement.setString(1, CommenterName);
                makeComment(listOfComments, statement);
                System.out.println(statement);
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        }
        return listOfComments;
    }

    private void makeComment(List<Comments> listOfComments, PreparedStatement statement) throws SQLException {
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
                System.out.println(listOfComments.size());
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    private Comments makeComment( PreparedStatement statement) throws SQLException {
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
        return null;
    }

    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setActicleId(articleID);
        comments.setContent(comment);
        comments.setUsername(commentName);
        comments.setCommentId(commentID);
        comments.setCommentedTime(commentTime);
    }


    public void AddingCommentsToDataBase(int ArticlesID, String CommenterName, String Comments) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES(?,?,?);")) {
                System.out.println(statement);
                statement.setInt(1, ArticlesID);
                statement.setString(2, CommenterName);
                statement.setString(3, Comments);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void editComments(String Comment, int CommentId) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Comments SET Comments = ? WHERE CommentID = ?;")) {
                System.out.println(statement);
                statement.setString(1, Comment);
                statement.setInt(2, CommentId);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Username already exist. Cannot create profile page.");
            e.printStackTrace();
        }
    }

    public Comments selectionLastComment() {
        Comments comments = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Comments ORDER BY CommentID DESC Limit 1;")) {
                comments = makeComment(statement);
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Comment not found");
            e.printStackTrace();
        }
        return comments;
    }
}
