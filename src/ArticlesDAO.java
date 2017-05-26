import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesDAO extends LoginPassing {

    private int ArticleID = 0;
    private String ArticleName = null;
    private String username = null;
    private String Content = null;
    private Date DateCreated =null;



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

    public void madeArticles(String ArticleName, String UserIDName, String content) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Articles (ArticlesName, UserIDName, Content) VALUES( ? ,?,?);"
            );
            {
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, content);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataArticles( String ArticleName,String content, int ArticleID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE Articles SET ArticlesName = ?, Content= ? WHERE ArticlesID = ?;"
            );
            {
                statement.setString(1, ArticleName);
                statement.setString(2, content);
                statement.setInt(3, ArticleID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Articles makeArticle(ResultSet resultSet) throws SQLException {
        Articles articles = new Articles();
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        username = resultSet.getString(3);
        Content = resultSet.getString(4);
        Date DateCreated = resultSet.getTimestamp(5);
        articles.setArticlename(ArticleName);
        articles.setArticleid(ArticleID);
        articles.setContent(Content);
        articles.setDatecreated(DateCreated);
        articles.setUsername(username);

        return articles;
    }

}
