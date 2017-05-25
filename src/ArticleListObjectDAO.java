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

    public List<Articles> selectionArticlesList(String UserIDName) {
        List<Articles> index = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated FROM Articles WHERE UserIDName = ?;"
            );
            {
                statement.setString(1, UserIDName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Articles articleListObject = new Articles();
                    int ArticleID = resultSet.getInt(1);
                    String ArticleName = resultSet.getString(2);
                    Date DateCreated = resultSet.getDate(3);
                    articleListObject.setArticleID(ArticleID);
                    articleListObject.setArticleName(ArticleName);
                    articleListObject.setDateCreated(DateCreated);
                    index.add(articleListObject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(index.size());
        return index;
    }

}
