import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class Comments {
    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public String getCommentName() {
        return CommentName;
    }

    public void setCommentName(String commentName) {
        CommentName = commentName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Date getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(Date commentTime) {
        CommentTime = commentTime;
    }

    public int getArticleID() {
        return ArticleID;
    }

    public void setArticleID(int articleID) {
        ArticleID = articleID;
    }

    private int CommentID;
    private String CommentName;
    private String Comment;
    private Date CommentTime;
    private int ArticleID;

}
