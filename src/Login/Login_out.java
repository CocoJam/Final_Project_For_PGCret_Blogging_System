package Login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by ljam763 on 23/05/2017.
 */

//
public class Login_out extends HttpServlet {

    //This is called currently by the logout button in the profile page.
    //The exact process: (1) get session; (2) destroys session; (3) dispatches back login page.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //Invalidate the user session for log out.
        session.invalidate();
        Cookie cookie = new Cookie("pagemark", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        req.getRequestDispatcher("login_page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
