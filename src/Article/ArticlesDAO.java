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

    public synchronized Articles selectionArticles(String articleName) throws NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
        return null;
    }

    public synchronized Articles selectionArticles(int articlesID) throws SQLException, NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
        return null;
    }

    //making a new article (DAO)
    public synchronized void madeArticles(String ArticleName, String categoryName, String UserIDName, String content) throws NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //updating existing article
    public synchronized Articles updateArticles(String ArticleName, String categoryName, String content, int ArticleID) throws SQLException,NullPointerException{
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
            throw new SQLException();
        }
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
            return selectionArticles(ArticleID);
    }

    public synchronized boolean deleteLike(String username,int ArticleID) throws NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized boolean updateLike(String username,int ArticleID) throws NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public synchronized int NumberLike(int ArticleID)throws NullPointerException {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT count(username) FROM likes WHERE ArticlesID = ?;")) {
                statement.setInt(1, ArticleID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Like.");
            e.printStackTrace();
            return 0;
        }
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
        return 0;
    }

    public synchronized boolean Liked(String username,int ArticleID) throws NullPointerException{
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
        catch (NullPointerException e) {
            System.out.println("Error. Article not found.");
            e.printStackTrace();
            throw new NullPointerException();
        }
        return false;
    }

    private synchronized Articles makeArticle(ResultSet resultSet) throws SQLException{
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

    private synchronized void ArticlesSetStatments(Articles articles, Date dateCreated) {
        articles.setArticlename(ArticleName);
        articles.setArticleid(ArticleID);
        articles.setCategory(categoryName);
        articles.setContent(Content);
        articles.setDatecreated(dateCreated);
        articles.setUsername(username);
        articles.setLikeNumber(likes);
    }
}
