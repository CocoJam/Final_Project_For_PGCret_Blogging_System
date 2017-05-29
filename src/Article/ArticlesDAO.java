package Article;

import Login.LoginPassing;

import java.sql.*;
import java.util.Date;


/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesDAO extends LoginPassing {

    //1. To simplify things, the connection and password checking mechanism has been inherited from the LoginPassing class.

    private int ArticleID = 0;
    private String ArticleName = null;
    private String username = null;
    private String Content = null;
    private Date DateCreated = null;


    // 1.1 As noted at 1. above, by calling super, the connection and password checking mechanism is inherited as part of the constructor.
    public ArticlesDAO() {
        super();
    }

    // 2. selectionArticles method to ready and edit articles
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


    //This overloaded method may be redundant, depends on whether user will be required to access by articleID alone, seems unlikely as all articles should have an ArticleName. (In refactoring essential to clarify and delete unnecessary overloaded methods). --Further clarified that this is used by the ArticleIndex.jsp to access list of articles, would it be possible to combine with selectionArticles(name), perhaps only using articlesID
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

    //3. This is when a new article is created, the prepared SQL statement is run
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

    //4. This is to update the article with any changes.
    public Articles updataArticles(String ArticleName, String content, int ArticleID) {
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


    // 5. This is to use the resultSet and create the variables for the Article Javabean (to be used by the JSP's to set the values of the Articles). (discuss with James that this is better to place inside of the selectionArticles, if overloading for selectingArticles not required.)

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

    // 6. Used in the above (5) to set the article values with the result to be used in the JSP. (Really an extension of makeArticle, essential perhaps better to combine into one eg. articles.setArticlename(result.getInt(1));
    private void ArticlesSetStatments(Articles articles, Date dateCreated) {
        articles.setArticlename(ArticleName);
        articles.setArticleid(ArticleID);
        articles.setContent(Content);
        articles.setDatecreated(dateCreated);
        articles.setUsername(username);
    }

}
