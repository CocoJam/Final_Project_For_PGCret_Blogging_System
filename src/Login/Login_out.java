package Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Login_out extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
//        session.setAttribute("password", null);
//        session.setAttribute("log",false);
//        session.setAttribute("ProfilePage.Registration" ,false);
        req.getRequestDispatcher("/login_page.jsp").forward(req,resp);
    }
}
