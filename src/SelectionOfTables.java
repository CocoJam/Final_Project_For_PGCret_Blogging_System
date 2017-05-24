import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class SelectionOfTables {

    private Connection conn;
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private int ArticleID;
    private String ArticleName;
    private String ArticleContent;
    private int  CommentID;
    private String CommentName;
    private String Comment;


    public void selectionUsersNames(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Password FROM UsersNAmes WHERE Username = ? AND Password = ?;"
            );
            {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    this.password = resultSet.getString(2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void selectionUsersProfile (String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Name ,Email, Address, Education, Ethnicity FROM UsersProfile WHERE Username = ?;"
            );
            {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    this.name = resultSet.getString(2);
                    this.email = resultSet.getString(3);
                    this.address = resultSet.getString(4);
                    this.education = resultSet.getString(5);
                    this.ethnicity = resultSet.getString(6);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void selectionArticles (String articleName) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content FROM Articles WHERE ArticlesID = ?;"
            );
            {
                statement.setString(1, articleName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.ArticleID = resultSet.getInt(1);
                    this.ArticleName = resultSet.getString(2);
                    this.username = resultSet.getString(3);
                    this.ArticleContent = resultSet.getString(4);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void selectionArticles (int articlesID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content FROM Articles WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.ArticleID = resultSet.getInt(1);
                    this.ArticleName = resultSet.getString(2);
                    this.username = resultSet.getString(3);
                    this.ArticleContent = resultSet.getString(4);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void selectionComments (int articlesID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments FROM UsersNAmes WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.CommentID = resultSet.getInt(1);
                    this.ArticleID = resultSet.getInt(2);
                    this.CommentName = resultSet.getString(3);
                    this.Comment = resultSet.getString(4);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
