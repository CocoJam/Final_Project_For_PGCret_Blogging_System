package ProfilePage;

import Article.ArticleListObjectDAO;
import Article.Articles;
import Friend.Friend;
import Friend.FriendDAO;
import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static Connection.ConnectionToTheDataBase.*;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This is the servlet which gets the POST and GET methods for the ProfilePage.jsp

public class ProfilePageServlet extends HttpServlet {
    public class innerclass {
        public ProfilePageDAO getProfilePageDAO() {
            return profilePageDAO;
        }

        public void setProfilePageDAO(ProfilePageDAO profilePageDAO) {
            this.profilePageDAO = profilePageDAO;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        ProfilePageDAO profilePageDAO;
        private String username;
    }
//    ProfilePageDAO profilePageDAO;
//    private String username;

    //    Grabs the profile page from the Database based on the username (stored in session)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            innerclass innerclass = new innerclass();
            innerclass.setProfilePageDAO(new ProfilePageDAO());
//        profilePageDAO = new ProfilePageDAO();
            innerclass.setUsername((String) session.getAttribute("username"));
            if (req.getParameter("clickedShowList") != null) {
                if (req.getParameter("clickedShowList").equals("clickedShowList")) {
                    List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(innerclass.username);
                    String message = "<table class=\"table table-striped table-hover table-responsive\" id=\"ArticleTable\"><tr><th>Article Names</th><th>Article Category</th><th>Date Created</th></tr>";
                    for (Articles articles : indexList) {
                        message += "<tr><td><a href=\"/Articles?acticleId=" + articles.getArticleid() + "\">" + articles.getArticlename() + "</a></td><td>" + articles.getCategory() + "</td><td>" + articles.getDatecreated() + "</td></tr>";
                    }
                    message += "</table>";
                    resp.getWriter().print(message);
                    return;
                }
            }
            ProfilePAge profilePAge = innerclass.profilePageDAO.getUsersProfile(innerclass.username);
            session.setAttribute("profileInfo", profilePAge);
            closingConnection();
            req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
    }

    //    The point of this: TODO need to cleanup connections from Login/Registration servlet (Maybe POST directly(?))
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            innerclass innerclass = new innerclass();
            innerclass.setProfilePageDAO(new ProfilePageDAO());
            if (req.getParameter("accessFriend") == null) {
                doPost(req, resp);
            } else {
                HttpSession session = req.getSession();
                if (req.getParameter("accessFriend").equals((String) session.getAttribute("username"))) {
                    req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    return;
                } else {
                    ProfilePAge profilePAge = innerclass.profilePageDAO.getUsersProfile(req.getParameter("accessFriend"));
                    if (profilePAge != null) {
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
        catch (Exception e){
            e.printStackTrace();
            cookieTracker(req,resp);
        }
    }
}
