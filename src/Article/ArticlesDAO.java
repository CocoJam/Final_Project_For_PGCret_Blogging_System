package Article;

import Connection.*;

import java.sql.*;
import java.util.Date;
import java.util.Locale;

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
    private String categoryName = null;
    private int likes = 0;

    public ArticlesDAO() {
        super();
    }

    public Articles selectionArticles(String articleName) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, UserIDName, Category, Content, SpecificDateCreated FROM Articles WHERE ArticlesName = ?;")) {
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

    public Articles selectionArticles(int articlesID) throws SQLException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, UserIDName, Category, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;")) {
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
            throw new SQLException();
        }
        return null;
    }

    //making a new article (DAO)
    public void madeArticles(String ArticleName, String categoryName, String UserIDName, String content) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Articles (ArticlesName, UserIDName, Category, Content) VALUES( ? ,? ,?, ?);")) {
                System.out.println(statement);
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, categoryName);
                statement.setString(4, content);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
        } catch (SQLException e) {
            System.out.println("Error creating article");
            e.printStackTrace();
        }
    }

    //updating existing article
    public Articles updateArticles(String ArticleName, String categoryName, String content, int ArticleID) throws SQLException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Articles SET ArticlesName = ?, Category = ?, Content= ? WHERE ArticlesID = ?;")) {
                System.out.println(statement);
                statement.setString(1, ArticleName);
                statement.setString(2, categoryName);
                statement.setString(3, content);
                statement.setInt(4, ArticleID);

                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
        } catch (SQLException e) {
            System.out.println("Error updating article.");
            e.printStackTrace();
        }
        try {
            return selectionArticles(ArticleID);
        } catch (SQLException e) {
            System.out.println("ARticlesDAO Error");
           throw new SQLException("updateArticles error");
        }
    }

    public boolean deleteLike(String username,int ArticleID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM likes WHERE username=? AND ArticlesID=?;")) {
                statement.setString(1, username);
                statement.setInt(2, ArticleID);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Like.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLike(String username,int ArticleID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO likes (username, ArticlesID) VALUES( ? ,?);")) {
                statement.setString(1, username);
                statement.setInt(2, ArticleID);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Like.");
            e.printStackTrace();
            return false;
        }
    }

    public int NumberLike(int ArticleID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT count(username) FROM likes WHERE ArticlesID = ?;")) {
                statement.setInt(1, ArticleID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1));
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Like.");
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public boolean Liked(String username,int ArticleID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT username, ArticlesID FROM likes WHERE username= ? AND ArticlesID = ?;")) {
                statement.setString(1, username);
                statement.setInt(2, ArticleID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Like.");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private Articles makeArticle(ResultSet resultSet) throws SQLException {
        Articles articles = new Articles();
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        username = resultSet.getString(3);
        categoryName = resultSet.getString(4);
        Content = resultSet.getString(5);
        Date DateCreated = resultSet.getTimestamp(6);
        ArticlesSetStatments(articles, DateCreated);
        closingConnection();
        return articles;
    }

    private void ArticlesSetStatments(Articles articles, Date dateCreated) {
        articles.setArticlename(ArticleName);
        articles.setArticleid(ArticleID);
        articles.setCategory(categoryName);
        articles.setContent(Content);
        articles.setDatecreated(dateCreated);
        articles.setUsername(username);
        articles.setLikeNumber(likes);
    }
}
