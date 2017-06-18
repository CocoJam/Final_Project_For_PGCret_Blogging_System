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
    private String categoryName = null;
    private Date DateCreated = null;
    private String UsernameID = null;
    private String ArticleContent = null;


    public ArticleListObjectDAO() {
        super();
    }

    //Selecting article content for displaying All article.
    public List<Articles> selectionAllArticlesList() throws NullPointerException{
        List<Articles> ListIndex = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, Category, SpecificDateCreated, UserIDName, Content FROM Articles;")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    addingArticlesIntoTheList(ListIndex, resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        return ListIndex;
    }


    //Selecting article content for displaying specific list (UserID parameter).
    public synchronized List<Articles> selectionArticlesList(String UserIDName) throws NullPointerException{
        List<Articles> ListIndex = new ArrayList<>();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName,Category ,SpecificDateCreated, UserIDName, Content FROM Articles WHERE UserIDName = ?;")) {
                statement.setString(1, UserIDName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    addingArticlesIntoTheList(ListIndex, resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new NullPointerException();
        }

        return ListIndex;
    }
    //Using the resultsSer to add new Article object into the list
    private synchronized void addingArticlesIntoTheList(List<Articles> listIndex, ResultSet resultSet) throws SQLException,NullPointerException{
        while (resultSet.next()) {
            Articles articleListObject = new Articles();
            sqlSetStatments(resultSet);
            articleListObjectSetStatments(articleListObject);
            listIndex.add(articleListObject);
        }
    }
//SQL set statements for article index list.
    private synchronized void sqlSetStatments(ResultSet resultSet) throws SQLException, NullPointerException{
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        categoryName = resultSet.getString(3);
        DateCreated = resultSet.getDate(4);
        UsernameID = resultSet.getString(5);
        ArticleContent = resultSet.getString(6);
    }
    //Setters for the article within the list for the information.
    private synchronized void articleListObjectSetStatments(Articles articleListObject) {
        articleListObject.setArticleid(ArticleID);
        articleListObject.setArticlename(ArticleName);
        articleListObject.setCategory(categoryName);
        articleListObject.setDatecreated(DateCreated);
        articleListObject.setContent(articleListObject.cuttingLines(ArticleContent));
        articleListObject.setFirstimage(articleListObject.FirstImg(ArticleContent));
        if (UsernameID != null) {
            articleListObject.setUsername(UsernameID);
        }
    }

}
