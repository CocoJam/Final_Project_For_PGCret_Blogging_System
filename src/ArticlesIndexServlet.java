import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesIndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        req.setAttribute("ArticleIndex", new ArticleListObjectDAO().selectionArticlesList(username));
        req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req,resp);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
