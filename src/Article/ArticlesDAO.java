package Article;

import Connection.*;

import java.sql.*;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

//Select the specific article based on the id of the article
    public synchronized Articles selectionArticles(int articlesID) throws SQLException, NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT ArticlesID, ArticlesName, UserIDName, Category, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;")) {
                
                statement.setInt(1, articlesID);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return makeArticle(resultSet);
                    }
                }
                
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw new SQLException();
        }
        return null;
    }

    //making a new article (DAO)
    public synchronized void madeArticles(String ArticleName, String categoryName, String UserIDName, String content) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Articles (ArticlesName, UserIDName, Category, Content) VALUES( ? ,? ,?, ?);")) {
                
                statement.setString(1, ArticleName);
                statement.setString(2, UserIDName);
                statement.setString(3, categoryName);
                statement.setString(4, content);
                
                statement.executeUpdate();
                
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //updating existing article
    public synchronized Articles updateArticles(String ArticleName, String categoryName, String content, int ArticleID) throws SQLException,NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Articles SET ArticlesName = ?, Category = ?, Content= ? WHERE ArticlesID = ?;")) {
                
                statement.setString(1, ArticleName);
                statement.setString(2, categoryName);
                statement.setString(3, content);
                statement.setInt(4, ArticleID);

                
                statement.executeUpdate();
                
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw new SQLException();
        }
        catch (NullPointerException e) {
            
            e.printStackTrace();
            throw new NullPointerException();
        }
            return selectionArticles(ArticleID);
    }

    //Deleting the like on this article based on id and the username who deletes or unlike it.
    public synchronized boolean deleteLike(String username,int ArticleID) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM likes WHERE username=? AND ArticlesID=?;")) {
                statement.setString(1, username);
                statement.setInt(2, ArticleID);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //To add like when some user liked the article.
    public synchronized boolean updateLike(String username,int ArticleID) throws NullPointerException{
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO likes (username, ArticlesID) VALUES( ? ,?);")) {
                statement.setString(1, username);
                statement.setInt(2, ArticleID);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    //Using to count and display how many likes had been given within this article.
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
            
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    //Using sql to match has this person liked the article already if so return true, otherwise return false.
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
            
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            
            e.printStackTrace();
            throw new NullPointerException();
        }
        return false;
    }

    //Using the sql result set to form a new Article object.
    private synchronized Articles makeArticle(ResultSet resultSet) throws SQLException{
        Articles articles = new Articles();
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        username = resultSet.getString(3);
        categoryName = resultSet.getString(4);
        Content = resultSet.getString(5);
        Date DateCreated = resultSet.getTimestamp(6);
        ArticlesSetStatments(articles, DateCreated);
        return articles;
    }
    //Using the article setters to set up the article's properties.
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
