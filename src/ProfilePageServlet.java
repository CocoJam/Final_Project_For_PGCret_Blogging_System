import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ProfilePageServlet extends HttpServlet{
    ProfilePageDAO profilePageDAO = new ProfilePageDAO();
    String username;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        ProfilePAge profilePAge = profilePageDAO.generateUsersProfile(username);
        req.setAttribute("profileInfo", profilePAge);
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }
}
