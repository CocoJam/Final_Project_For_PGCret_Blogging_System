package Multi_media;

import Connection.ConnectionToTheDataBase;
import Login.LoginPassing;
import Login.Passwords_Checker;
import ProfilePage.ProfilePAge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class MediaDAO {

//    public void addingYoutubeToDatabase(int ArticleID, String youtubeurl) {
//        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
//            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO youtube (ArticlesID, youtubeURL) VALUES(?,?);")) {
//                String youtube = "<iframe width=\"854\" height=\"480\" src=\"" + youtubeurl + "\" frameborder=\"0\" allowfullscreen></iframe>";
//                youtube.replace("watch?v=", "embed/");
//                System.out.println(statement);
//                statement.setInt(1, ArticleID);
//                statement.setString(2, youtube);
//                System.out.println(statement);
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public List<String> gettingAllYoutube(int ArticleID) {
//        List<String> youtubeList = new ArrayList<>();
//        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
//            try (PreparedStatement statement = connection.prepareStatement("SELECT youtubeURL FROM youtube WHERE ArticlesId = ?;")) {
//                System.out.println(statement);
//                statement.setInt(1, ArticleID);
//                System.out.println(statement);
//                statement.execute();
//                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        youtubeList.add(resultSet.getString(1));
//                    }
//                }
//                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
//            }
//            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return youtubeList;
//    }
}
