package ProfilePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.conn;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This is the servlet which gets the POST and GET methods for the ProfilePage.jsp

public class ProfilePageServlet extends HttpServlet{
    ProfilePageDAO profilePageDAO;
    private String username;

//    Grabs the profile page from the Database based on the username (stored in session)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        profilePageDAO = new ProfilePageDAO();
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        ProfilePAge profilePAge = profilePageDAO.getUsersProfile(username);
        session.setAttribute("profileInfo", profilePAge);
        session.setAttribute("articleList", "self"); //added in to ensure that the articles displaying on the Profile page is self only.
        closingConnection();
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }

//    The point of this: TODO need to cleanup connections from Login/Registration servlet (Maybe POST directly(?))
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sdgfdrjgkldjglkdfjglkdfjglkdfjglkdfjglkdjlgdfj");
        doPost(req,resp);
    }
}
