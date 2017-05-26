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
    private ArticlesDAO articlesDAO = new ArticlesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int ArticleID = Integer.parseInt(req.getParameter("articleID"));
        Articles article = articlesDAO.selectionArticles(ArticleID);
        session.setAttribute("articleContents", article);
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addingArticles = req.getParameter("add");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (addingArticles != null) {
            if (addingArticles.equals("addNewArticle")) {
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;
            }
            if (addingArticles.equals("addingToDataBase")) {
                String ArticleName = req.getParameter("ArticleName");
                String ArticleContent = req.getParameter("ArticleContent");
                articlesDAO.updataArticles(ArticleName, username, ArticleContent);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
                return;
            }
        }
    }
}
