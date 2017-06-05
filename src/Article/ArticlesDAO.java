package Article;

import Connection.*;

import java.sql.*;
import java.util.Date;

import static Connection.ConnectionToTheDataBase.closingConnection;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesDAO {

    private int ArticleID = 0;
    private String ArticleName = null;
    private String username = null;
    private String Content = null;
    private Date DateCreated = null;


    public ArticlesDAO() {
        super();
    }

    public Articles selectionArticles(String articleName) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesName = ?;")) {
                System.out.println(statement);
                statement.setString(1, articleName);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return makeArticle(resultSet);
                    }
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
        }
        return null;
    }


    public Articles selectionArticles(int articlesID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;")) {
                System.out.println(statement);
                statement.setInt(1, articlesID);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return makeArticle(resultSet);
                    }
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
        }
        return null;
    }

    public void madeArticles(String ArticleName, String UserIDName, String content) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Articles (ArticlesName, UserIDName, Content) VALUES( ? ,?,?);")) {
                System.out.println(statement);
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, content);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
        } catch (SQLException e) {
            System.out.println("Error creating article");
            e.printStackTrace();
        }
    }

    public Articles updateArticles(String ArticleName, String content, int ArticleID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Articles SET ArticlesName = ?, Content= ? WHERE ArticlesID = ?;")) {
                System.out.println(statement);
                statement.setString(1, ArticleName);
                statement.setString(2, content);
                statement.setInt(3, ArticleID);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
        } catch (SQLException e) {
            System.out.println("Error updating article.");
            e.printStackTrace();
        }
        return selectionArticles(ArticleID);
    }

    private Articles makeArticle(ResultSet resultSet) throws SQLException {
        Articles articles = new Articles();
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        username = resultSet.getString(3);
        Content = resultSet.getString(4);
        Date DateCreated = resultSet.getTimestamp(5);
        ArticlesSetStatments(articles, DateCreated);
        closingConnection();
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
