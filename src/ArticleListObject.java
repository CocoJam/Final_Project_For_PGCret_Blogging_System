import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticleListObject {
//Java Bean cant access needed to be checked
    private int ArticlesID;
    private String ArticlesName;
    private Date SpecificDateCreated;

    public int getArticlesID() {
        return ArticlesID;
    }

    public void setArticlesID(int articlesID) {
        ArticlesID = articlesID;
    }

    public String getArticlesName() {
        return ArticlesName;
    }

    public void setArticlesName(String articlesName) {
        ArticlesName = articlesName;
    }

    public Date getSpecificDateCreated() {
        return SpecificDateCreated;
    }

    public void setSpecificDateCreated(Date specificDateCreated) {
        SpecificDateCreated = specificDateCreated;
    }



}
