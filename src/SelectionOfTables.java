import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class SelectionOfTables {


    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getEducation() {
        return education;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public int getArticleID() {
        return ArticleID;
    }

    public String getArticleName() {
        return ArticleName;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public Timestamp getSpecificDateCreated() {
        return SpecificDateCreated;
    }

    public int getCommentID() {
        return CommentID;
    }

    public String getCommentName() {
        return CommentName;
    }

    public String getComment() {
        return Comment;
    }

    public Timestamp getCommentTime() {
        return CommentTime;
    }

    public Connection getConn() {
        return conn;
    }

    private String username;
    private int userID;
    private String password;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private int ArticleID;
    private String ArticleName;
    private String ArticleContent;
    private Timestamp SpecificDateCreated;
    private int  CommentID;
    private String CommentName;
    private String Comment;
    private Timestamp CommentTime;
    private Connection conn;
    private Passwords_Checker pass;

    public SelectionOfTables() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        this.pass = new Passwords_Checker();
    }

    public void selectionUsersNames(String username, String password) {
        try {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Password FROM UsersNames WHERE Username = ? AND Password = ?;"
            );
            {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.username = resultSet.getString(1);
                    System.out.println(username);
                    this.password = resultSet.getString(2);
                    System.out.println(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void selectionUsersProfile (String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Name ,Email, Address, Education, Ethnicity, UserId FROM UserNames WHERE Username = ?;"
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
                    this.userID = resultSet.getInt(7);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void selectionArticles (String articleName) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;"
            );
            {
                statement.setString(1, articleName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.ArticleID = resultSet.getInt(1);
                    this.ArticleName = resultSet.getString(2);
                    this.username = resultSet.getString(3);
                    this.ArticleContent = resultSet.getString(4);
                    this.SpecificDateCreated = resultSet.getTimestamp(5);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void selectionArticles (int articlesID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName , UserIDName, Content, SpecificDateCreated FROM Articles WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.ArticleID = resultSet.getInt(1);
                    this.ArticleName = resultSet.getString(2);
                    this.username = resultSet.getString(3);
                    this.ArticleContent = resultSet.getString(4);
                    this.SpecificDateCreated = resultSet.getTimestamp(5);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void selectionComments (int articlesID) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT CommentID, ArticlesID , CommenterName, Comments, CommentTime FROM UsersNAmes WHERE ArticlesID = ?;"
            );
            {
                statement.setInt(1, articlesID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    this.CommentID = resultSet.getInt(1);
                    this.ArticleID = resultSet.getInt(2);
                    this.CommentName = resultSet.getString(3);
                    this.Comment = resultSet.getString(4);
                    this.CommentTime = resultSet.getTimestamp(5);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //testing grounds here
        SelectionOfTables selectionOfTables = new SelectionOfTables();
        selectionOfTables.selectionUsersNames("ljam763", "blah");
        System.out.println( selectionOfTables.getUsername());
    }
}
