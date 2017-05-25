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
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ArticleID = Integer.parseInt(req.getParameter("articleID"));
        ArticlesDAO articlesDAO = new ArticlesDAO();
        Articles article= articlesDAO.selectionArticles(ArticleID);
        req.setAttribute("articleContents", article);
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req,resp);
        return;
    }
}
