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
public class CommentsDAO extends LoginPassing {

    public CommentsDAO() {
        super();
    }

    public List<Comments> selectionComments(int CommentID, int articleID) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM UsersNAmes WHERE ArticlesID = ? AND CommentID = ?;"
            );
            {
                statement.setInt(2, CommentID);
                statement.setInt(1, articleID);
                makeComment(listOfComments, statement);
            }
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return listOfComments;
    }


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
            System.out.println("Comments not here");
            e.printStackTrace();
        }
        return listOfComments;
    }

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
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return listOfComments;
    }

    private void makeComment(List<Comments> listOfComments, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Comments comments = new Comments();
            int CommentID = resultSet.getInt(1);
            int ArticleID = resultSet.getInt(2);
            String CommentName = resultSet.getString(3);
            String  Comment = resultSet.getString(4);
            Date CommentTime = resultSet.getTimestamp(5);
            commentsSetStatments(comments, CommentID, ArticleID, CommentName, Comment, CommentTime);
            listOfComments.add(comments);
            System.out.println(listOfComments.size());
        }
    }

    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setActicleId(articleID);
        comments.setContent(comment);
        comments.setUsername(commentName);
        comments.setCommentId(commentID);
        comments.setCommentedTime(commentTime);
    }


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
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void editComments(String Comment, int CommentId){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE  Comments SET Comments=? WHERE CommentID=?;"
            );
            {
                statement.setString(1, Comment);
                statement.setInt(2, CommentId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
