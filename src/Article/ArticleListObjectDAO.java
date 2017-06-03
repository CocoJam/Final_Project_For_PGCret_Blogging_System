package Article;

import Connection.ConnectionToTheDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticleListObjectDAO extends ArticlesDAO {

    private int ArticleID = 0;
    private String ArticleName = null;
    private Date DateCreated = null;
    private String UsernameID = null;
    private ConnectionToTheDataBase connectionToTheDataBase;
    private Connection conn;


    public ArticleListObjectDAO() {
        connectionToTheDataBase = new ConnectionToTheDataBase();
    }

    public List<Articles> selectionAllArticlesList() {
        List<Articles> ListIndex = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated, UserIDName FROM Articles;"
            );
            {
                ResultSet resultSet = statement.executeQuery();
                addingArticlesIntoTheList(ListIndex, resultSet);
            }
            conn.close();
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        System.out.println("Article size" + ListIndex.size());
        return ListIndex;
    }


//    public List<Articles> selectionAllArticlesList() {
//        List<Articles> ListIndex = new ArrayList<>();
//        try( Connection conn = connectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
//            PreparedStatement statement = conn.prepareStatement(
//                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated, UserIDName FROM Articles;"
//            )) {
//            {
//                ResultSet resultSet = statement.executeQuery();
//                addingArticlesIntoTheList(ListIndex, resultSet);
//            }
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Article size" + ListIndex.size());
//        try {
//            System.out.println("is that dataBase closed? "+ conn.isClosed());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ListIndex;
//    }


    public List<Articles> selectionArticlesList(String UserIDName) {
        List<Articles> ListIndex = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT ArticlesID, ArticlesName,SpecificDateCreated, UserIDName FROM Articles WHERE UserIDName = ?;"
            );
            {
                statement.setString(1, UserIDName);
                ResultSet resultSet = statement.executeQuery();
                addingArticlesIntoTheList(ListIndex, resultSet);
            }
            conn.close();
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        System.out.println("Article size" + ListIndex.size());
        return ListIndex;
    }

    private void addingArticlesIntoTheList(List<Articles> listIndex, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Articles articleListObject = new Articles();
            sqlSetStatments(resultSet);
            articleListObjectSetStatments(articleListObject);
            listIndex.add(articleListObject);
        }
    }

    private void sqlSetStatments(ResultSet resultSet) throws SQLException {
        ArticleID = resultSet.getInt(1);
        ArticleName = resultSet.getString(2);
        DateCreated = resultSet.getDate(3);
        UsernameID = resultSet.getString(4);
    }

    private void articleListObjectSetStatments(Articles articleListObject) {
        articleListObject.setArticleid(ArticleID);
        articleListObject.setArticlename(ArticleName);
        articleListObject.setDatecreated(DateCreated);
        if (UsernameID != null) {
            System.out.println("userId set");
            articleListObject.setUsername(UsernameID);
        }
    }

}
