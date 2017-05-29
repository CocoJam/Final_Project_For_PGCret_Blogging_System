package Article;

import Login.LoginPassing;

import java.sql.*;
import java.util.Date;


/**
 * Created by ljam763 on 25/05/2017.
 */

//0. ArticlesDAO which is instantiated by the ArticlesServlet and has various methods for the ArticlesServlet to add, select or edit certain Article Database entries.

public class ArticlesDAO extends LoginPassing {

    //0.a. Global variables which are used by the various methods.

    private int ArticleID = 0;
    private String ArticleName = null;
    private String username = null;
    private String Content = null;
    private Date DateCreated =null;

    //0.b. The constructor uses the super ie LoginPassing's constructor, this way the login details are kept hidden within the LoginPassing class.
    public ArticlesDAO() {
        super();
    }

    //1. This method allows the ArticleServlet to access a particular article using the articleName.

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


    //2. This method allows the ArticleServlet to access a particular article using the articleID.
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

    //3. This is to make an article, using ArticleName, UserIDNAme and content values to be provided by the ArticlesServlet (which in turn receives the values from the client)
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

    //4. This is to update an article, using ArticleName, content and ArticleID to be provided by the ArticlesServlet (which in turn receives the values from the client browser).
    public Articles updataArticles( String ArticleName,String content, int ArticleID) {
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
        return selectionArticles(ArticleID);
    }


    //5. Not to be confused with madeArticle - this method is used by the selectionArticle methods to display Articles by declaring and instantiating an Article Object and setting the values of the JavaBean.
    private Articles makeArticle(ResultSet resultSet) throws SQLException {
        Articles articles = new Articles();
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        username = resultSet.getString(3);
        Content = resultSet.getString(4);
        Date DateCreated = resultSet.getTimestamp(5);
        ArticlesSetStatments(articles, DateCreated);
        return articles;
    }

    private void ArticlesSetStatments(Articles articles, Date dateCreated) {
        articles.setArticlename(ArticleName);
        articles.setArticleid(ArticleID);
        articles.setContent(Content);
        articles.setDatecreated(dateCreated);
        articles.setUsername(username);
    }

}
