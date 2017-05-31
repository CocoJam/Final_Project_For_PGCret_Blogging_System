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
public class ProfilePageServlet extends HttpServlet{
    ProfilePageDAO profilePageDAO;
    private String username;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        profilePageDAO = new ProfilePageDAO();
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        ProfilePAge profilePAge = profilePageDAO.getUsersProfile(username);
        session.setAttribute("profileInfo", profilePAge);
        session.setAttribute("log","ChangeUserInformation");
        closingConnection();
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
