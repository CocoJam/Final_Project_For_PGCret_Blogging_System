import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticleListObjectDAO extends ArticlesDAO {

    private int ArticleID = 0;
    private String ArticleName = null;
    private Date DateCreated =null;


    public ArticleListObjectDAO() {
        super();
    }

    public List<Articles> selectionArticlesList(String UserIDName) {
        List<Articles> ListIndex = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated FROM Articles WHERE UserIDName = ?;"
            );
            {
                statement.setString(1, UserIDName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Articles articleListObject = new Articles();
                    sqlSetStatments(resultSet);
                    articleListObjectSetStatments(articleListObject);
                    ListIndex.add(articleListObject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Article size" + ListIndex.size());
        return ListIndex;
    }

    private void sqlSetStatments(ResultSet resultSet) throws SQLException {
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        DateCreated = resultSet.getDate(3);
    }

    private void articleListObjectSetStatments(Articles articleListObject) {
        articleListObject.setArticleid(ArticleID);
        articleListObject.setArticlename(ArticleName);
        articleListObject.setDatecreated(DateCreated);
    }

}
