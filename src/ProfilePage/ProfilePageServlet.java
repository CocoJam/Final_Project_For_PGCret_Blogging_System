package ProfilePage;

import Article.ArticleListObjectDAO;
import Article.Articles;
import Friend.Friend;
import Friend.FriendDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.conn;
import static Connection.ConnectionToTheDataBase.cookieLogOut;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This is the servlet which gets the POST and GET methods for the ProfilePage.jsp

public class ProfilePageServlet extends HttpServlet {
    ProfilePageDAO profilePageDAO;
    private String username;

    //    Grabs the profile page from the Database based on the username (stored in session)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        profilePageDAO = new ProfilePageDAO();
        username = (String) session.getAttribute("username");

        if (req.getParameter("clickedShowList") != null) {

            if (req.getParameter("clickedShowList").equals("clickedShowList")) {
                List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(username);
                String message = "<table class=\"table table-striped table-hover table-responsive\" id=\"ArticleTable\">\n" +
                        "                                <tr>\n" +
                        "                                    <th>\n" +
                        "                                        Article Names\n" +
                        "                                    </th>\n" +
                        "                                    <th>\n" +
                        "                                        Article Category\n" +
                        "                                    </th>\n" +
                        "                                    <th>\n" +
                        "                                        Date Created\n" +
                        "                                    </th>\n" +
                        "\n" +
                        "\n" +
                        "                                </tr>\n";

                for (Articles articles : indexList) {
                    message +=
                            "<tr>\n" +
                            "                                        <td>\n" +
                            "                                            <a href=\"/Articles?acticleId=" + articles.getArticleid() + "\">" + articles.getArticlename() + "</a>\n" +
                            "                                        </td>\n" +
                            "\n" +
                            "                                        <td>\n" +
                                                                            articles.getCategory() +
                            "                                        </td>\n" +
                            "\n" +
                            "                                        <td>" + articles.getDatecreated() + "</td>\n" +
                            "\n" +
                            "\n" +
                            "                                    </tr>\n";
                }

                message += "</table>";

//            JSONObject jsonObject = getJsonListObjects(indexList);

                resp.getWriter().print(message);
                return;
            }
        }

//        NB. Following commented out lines are redundant, as login does this functionality.
//        List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(username);
//        session.setAttribute("IndexOfInterest", indexList);
//        session.setAttribute("articleList", "self"); //added in to ensure that the articles displaying on the Profile page is self only.

        ProfilePAge profilePAge = profilePageDAO.getUsersProfile(username);
        session.setAttribute("profileInfo", profilePAge);
        closingConnection();
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
        return;
    }

//    The point of this: TODO need to cleanup connections from Login/Registration servlet (Maybe POST directly(?))
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("accessFriend") == null) {
            System.out.println("sdgfdrjgkldjglkdfjglkdfjglkdfjglkdfjglkdjlgdfj");
            doPost(req, resp);
        cookieLogOut(req,resp);
        System.out.println("in pp doget");
        if (req.getParameter("accessFriend") == null){
            System.out.println("accessFirend == null");
            doPost(req,resp);
            return;
        } else {
            ProfilePAge profilePAge = profilePageDAO.getUsersProfile(req.getParameter("accessFriend"));
            System.out.println(profilePAge + " is there anyone here?");
            if (profilePAge != null) {
                HttpSession session = req.getSession();
                List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(req.getParameter("accessFriend"));
                List<Friend> friendList = new FriendDAO().selectionListOfFriends(req.getParameter("accessFriend"));
                session.setAttribute("accessFriendfirendlist", friendList);
                System.out.println("Firend list" + friendList);
                System.out.println(req.getParameter("accessFriend"));
                session.setAttribute("IndexOfInterest", indexList);
                session.setAttribute("showFriend", profilePAge);
            }
            req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
            return;
        }
    }
}
