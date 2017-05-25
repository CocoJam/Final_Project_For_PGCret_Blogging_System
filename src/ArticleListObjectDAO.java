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
    public ArticleListObjectDAO() {
        super();
    }

    public List<ArticleListObject> selectionArticlesList(String UserIDName) {
        List<ArticleListObject> index = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated FROM Articles WHERE UserIDName = ?;"
            );
            {

                statement.setString(1, UserIDName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ArticleListObject articleListObject = new ArticleListObject();
                    int ArticleID = resultSet.getInt(1);
                    String ArticleName = resultSet.getString(2);
                    Date DateCreated = resultSet.getDate(3);
                    articleListObject.setArticlesID(ArticleID);
                    articleListObject.setArticlesName(ArticleName);
                    articleListObject.setSpecificDateCreated(DateCreated);
                    index.add(articleListObject);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

}
