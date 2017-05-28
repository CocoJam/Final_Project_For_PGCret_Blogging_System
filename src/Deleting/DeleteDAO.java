package Deleting;

import Login.LoginPassing;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeleteDAO extends LoginPassing {

    public DeleteDAO() {
        super();
    }


    public void dropSpeificComment (int CommentID){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM Articles WHERE CommentID=?;"
            );
            statement.setInt(1, CommentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropSpeificArticle (int ArticlesID){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM Articles WHERE ArticlesID=?;"
            );
            statement.setInt(1, ArticlesID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropComments(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM Comments WHERE CommenterName=?;"
            );
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropArticles(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM Articles WHERE UserIDName=?;"
            );
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUserInformation(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM UsersNames WHERE Username=?;"
            );
            statement.setString(1, username);
            System.out.println("deleting");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropAllByUsername(String username) {
        dropArticles(username);
        dropComments(username);
        dropUserInformation(username);
    }
}
