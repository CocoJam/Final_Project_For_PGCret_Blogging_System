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

    public void lodgeComment (int articleId, String commenterName, String commentContent) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Comments (ArticlesID, CommenterName, Comments) VALUES (?,?,?);");
            {
                System.out.println(articleId);
                System.out.println(commenterName);
                System.out.println(commentContent);

                statement.setInt(1, articleId);
                statement.setString(2, commenterName);
                statement.setString(3, commentContent);
                statement.executeUpdate();

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Comments> selectionComments(int CommentID, int articleID) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM uoaslashn.Comments WHERE CommentID = ? AND ArticlesID = ?;"
            );
            {
                statement.setInt(1, CommentID);
                statement.setInt(2, articleID);
                makeComment(listOfComments, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfComments;
    }


    public List<Comments> selectionComments(int articlesID) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM UsersNames WHERE ArticlesID = ?;"
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

    public List<Comments> selectionComments(String CommenterName) {
        List<Comments> listOfComments = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM UsersNames WHERE CommenterName = ?;"
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
//
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
        }
    }

    private void commentsSetStatments(Comments comments, int commentID, int articleID, String commentName, String comment, Date commentTime) {
        comments.setArticleID(articleID);
        comments.setComment(comment);
        comments.setCommentName(commentName);
        comments.setCommentID(commentID);
        comments.setCommentTime(commentTime);
    }
}
