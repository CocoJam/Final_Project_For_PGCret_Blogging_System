package Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Connection.*;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticleListObjectDAO extends ArticlesDAO {

    private int ArticleID = 0;
    private String ArticleName = null;
    private Date DateCreated = null;
    private String UsernameID = null;
    private String ArticleContent = null;


    public ArticleListObjectDAO() {
        super();
    }

    public List<Articles> selectionAllArticlesList() {
        List<Articles> ListIndex = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, SpecificDateCreated, UserIDName, Content FROM Articles;")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    addingArticlesIntoTheList(ListIndex, resultSet);
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
        System.out.println("Article size" + ListIndex.size());
        return ListIndex;
    }


    public List<Articles> selectionArticlesList(String UserIDName) {
        List<Articles> ListIndex = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, SpecificDateCreated, UserIDName, Content FROM Articles WHERE UserIDName = ?;")) {
                System.out.println(statement);
                statement.setString(1, UserIDName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    addingArticlesIntoTheList(ListIndex, resultSet);
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error article not found");
            e.printStackTrace();
        }
        System.out.println("Article size" + ListIndex.size());
        return ListIndex;
    }

    private void addingArticlesIntoTheList(List<Articles> listIndex, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Articles articleListObject = new Articles();
            sqlSetStatments(resultSet);
            articleListObjectSetStatments(articleListObject);
            listIndex.add(articleListObject);
        }
    }

    private void sqlSetStatments(ResultSet resultSet) throws SQLException {
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        DateCreated = resultSet.getDate(3);
        UsernameID = resultSet.getString(4);
        ArticleContent = resultSet.getString(5);
    }

    private void articleListObjectSetStatments(Articles articleListObject) {

        articleListObject.setArticleid(ArticleID);
        articleListObject.setArticlename(ArticleName);
        articleListObject.setDatecreated(DateCreated);
        articleListObject.setContent(articleListObject.cuttingLines(ArticleContent));
        articleListObject.setFirstimage(articleListObject.FirstImg(ArticleContent));
        if (UsernameID != null) {
            System.out.println("userId set");
            articleListObject.setUsername(UsernameID);
        }
    }

}
