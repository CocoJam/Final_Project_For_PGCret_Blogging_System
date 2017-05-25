import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class Articles {
    public int getArticleID() {
        return ArticleID;
    }

    public void setArticleID(int articleID) {
        ArticleID = articleID;
    }

    public String getArticleName() {
        return ArticleName;
    }

    public void setArticleName(String articleName) {
        ArticleName = articleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    private int ArticleID;
    private String ArticleName;
    private String username;
    private String Content;
    private Date DateCreated;

}
