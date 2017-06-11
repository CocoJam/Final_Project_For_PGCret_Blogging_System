package Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 11/06/2017.
 */
public class ArticleCartServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session= req.getSession();

        if ((List<String>) session.getAttribute("cartlist") == null) {
            List<String> cartlist = new ArrayList<>();
            session.setAttribute("cartlist", cartlist);
            System.out.println("set a list");
        }
        if (req.getParameter("cartunadd") != null) {
            System.out.println("cartunadd");
            System.out.println(req.getParameter("cartunadd"));
            if ((List<String>) session.getAttribute("cartlist") != null) {
                System.out.println("unadd");
                ((List<String>) session.getAttribute("cartlist")).remove(req.getParameter("cartunadd"));
            }
            System.out.println((List<String>)session.getAttribute("cartlist"));
            System.out.println("where?1");
        }
        if (req.getParameter("cartadd") != null) {
            System.out.println("cartadd");
            System.out.println(req.getParameter("cartadd"));
            if ((List<String>) session.getAttribute("cartlist") != null) {
                System.out.println("add");
                ((List<String>) session.getAttribute("cartlist")).add(req.getParameter("cartadd"));
            }
            System.out.println((List<String>)session.getAttribute("cartlist"));
            System.out.println("where?2");
        }
    }
}
