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
        //session.invalidate() fucks with the logout and then registration system.
//        session.invalidate();
        System.out.println("Logging out");
        session.setAttribute("password", null);
        session.setAttribute("log",false);
        session.setAttribute("profileInfo",null);
        session.setAttribute("articleID", null);
        session.setAttribute("Registration" ,false);
        session.setAttribute("ArticleIndex",null);
        session.setAttribute("articleContents",null);
        req.getRequestDispatcher("/login_page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
