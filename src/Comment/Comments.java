package Comment;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */

//Javabean for Comments.

public class Comments implements Serializable {
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentedTime() {
        return commentedTime;
    }

    public void setCommentedTime(Date commentedTime) {
        this.commentedTime = commentedTime;
    }

    public int getActicleId() {
        return acticleId;
    }

    public void setActicleId(int acticleId) {
        this.acticleId = acticleId;
    }

    private int commentId;
    private String username;
    private String content;
    private Date commentedTime;
    private int acticleId;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    private boolean owner= false;

}
