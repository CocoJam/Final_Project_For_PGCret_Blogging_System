package Deleting;

import Connection.ConnectionToTheDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeleteDAO {

    public DeleteDAO() {
        super();
    }


    public void dropSpeificComment(int CommentID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE CommentID = ?;")) {
                System.out.println(CommentID);
                System.out.println(statement);
                statement.setInt(1, CommentID);
                System.out.println(statement);
                statement.executeUpdate();
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }


    public void dropSpeificArticle(int ArticlesID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try {
                dropArticleAllComments(ArticlesID);
                try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Articles WHERE ArticlesID = ?;")) {
                    System.out.println(statement);
                    statement.setInt(1, ArticlesID);
                    System.out.println(statement);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println("Error creating database connection.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void dropArticleAllComments(int ArticlesID) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE ArticlesID = ?;")) {
                System.out.println(statement);
                statement.setInt(1, ArticlesID);
                System.out.println(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void dropComments(String username) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Comments WHERE CommenterName = ?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                System.out.println(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void dropArticles(String username) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Articles WHERE UserIDName = ?;")) {
                statement.setString(1, username);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void dropUserInformation(String username) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM UsersNames WHERE Username = ?;")) {
                statement.setString(1, username);
                System.out.println("deleting");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error creating database connection.");
            e.printStackTrace();
        }
    }

    public void dropSpeificYoutube(String youtubeURL) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM youtube WHERE youtubeURL LIKE '%'?'%';"
            );
            statement.setString(1, youtubeURL);
            System.out.println("deleting");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropAllByUsername(String username) {
        dropComments(username);
        dropArticles(username);
        dropUserInformation(username);

    }
}
