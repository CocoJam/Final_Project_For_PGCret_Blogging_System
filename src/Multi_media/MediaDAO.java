package Multi_media;

import Connection.ConnectionToTheDataBase;
import Login.LoginPassing;
import Login.Passwords_Checker;
import ProfilePage.ProfilePAge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class MediaDAO extends LoginPassing {
    public MediaDAO() {
        this.conn = ConnectionToTheDataBase.conn;
    }

    public void addingYoutubeToDatabase(int ArticleID, String youtubeurl) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO youtube (ArticlesID , youtubeURL) VALUES( ? ,?);"
            );
            {
                statement.setInt(1, ArticleID);
                statement.setString(2, youtubeurl);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> gettingAllYoutube(int ArticleID) {
        List<String> youtubeList = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT youtubeURL FROM youtube WHERE ArticlesId =?;"
            );
            {
                statement.setInt(1, ArticleID);
                statement.execute();
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    youtubeList.add(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return youtubeList;
    }
}
