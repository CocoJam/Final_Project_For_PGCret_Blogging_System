import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesDAO extends LoginPassing {

    public ArticlesDAO() {
        super();
    }

    public Articles selectionArticles(String articleName) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesName = ?;"
            );
            {
                statement.setString(1, articleName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                return makeArticle(resultSet);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Articles selectionArticles(int articlesID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    return makeArticle(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Articles makeArticle(ResultSet resultSet) throws SQLException {
        Articles articles = new Articles();
        int ArticleID = resultSet.getInt(1);
        String ArticleName = resultSet.getString(2);
        String username = resultSet.getString(3);
        String Content = resultSet.getString(4);
        Date DateCreated = resultSet.getTimestamp(5);
        articles.setArticleName(ArticleName);
        articles.setArticleID(ArticleID);
        articles.setContent(Content);
        articles.setDateCreated(DateCreated);
        articles.setUsername(username);
        return articles;
    }

}
