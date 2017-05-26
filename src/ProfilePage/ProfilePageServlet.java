package ProfilePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ProfilePageServlet extends HttpServlet{
    ProfilePageDAO profilePageDAO = new ProfilePageDAO();
    private String username;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private Date date;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        ProfilePAge profilePAge = profilePageDAO.getUsersProfile(username);
        session.setAttribute("profileInfo", profilePAge);
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
