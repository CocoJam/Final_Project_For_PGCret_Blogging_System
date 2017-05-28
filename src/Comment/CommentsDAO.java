package Comment;

import Login.LoginPassing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */

//0. Comments DAO to access the Database. This is called by the CommentsServlet class which will in turn establish the request and response with the client.

public class CommentsDAO extends LoginPassing {

    public CommentsDAO() {
        super();
    }


    //1.a the below method allows selecting comments through inputting the CommentID and articleId parameters.
    public List<Comments> selectionComments(int CommentID, int articleID) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM UsersNAmes WHERE ArticlesID = ? AND CommentID = ?;"
            );
            {
                statement.setInt(1, articleID);
                statement.setInt(2, CommentID);
                makeComment(listOfComments, statement); // goes to makeComment() method to get the resultSet (which in turn sets the Comments Javabean through commentsSetStatements section.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComments;
    }

    //1.b the below method allows selecting comments through inputing the articlesID only (For accessing multiple comments at once).
    public List<Comments> selectionComments(int articlesID) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                makeComment(listOfComments, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComments;
    }


    //1.c this method allows selecting comments through commenterName only.
    public List<Comments> selectionComments(String CommenterName) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM Comments WHERE CommenterName = ?;"
            );
            {
                statement.setString(1, CommenterName);
                makeComment(listOfComments, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComments;
    }

    //2. This method allows making comments and Storing to the Database.
    private void makeComment(List<Comments> listOfComments, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
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
    }

    //3. This method is to allow both selecting and making methods to easily set the comment details (in conjunction with the AddingCommentsToDataBase method which sets the Javabean).
    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setActicleId(articleID);
        comments.setContent(comment);
        comments.setUsername(commentName);
        comments.setCommentId(commentID);
        comments.setCommentedTime(commentTime);
    }


    //4. Sets the Comments Javabean for access by the client.
    public void AddingCommentsToDataBase(int ArticlesID, String CommenterName, String Comments) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES( ?, ? ,?);"
            );
            {
                statement.setInt(1, ArticlesID);
                statement.setString(2, CommenterName);
                statement.setString(3, Comments);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
