package Article;

import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This class is the javabean for articles. It allows us to add Article Objects into the ArticleListObjectDAO
public class Articles {

    private int articleid = 0;
    private String articlename = null;
    private String username = null;
    private String content = null;
    private Date datecreated = null;

    //Ask James why there is an empty constructor at present?
    public Articles() {
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
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

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    private boolean owner = false;





}
