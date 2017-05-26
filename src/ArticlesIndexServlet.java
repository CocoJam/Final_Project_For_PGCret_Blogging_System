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
public class ArticlesIndexServlet extends HttpServlet {
    private List<Articles> indexList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("index doGetting");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (indexList == null){
            List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(username);
            session.setAttribute("ArticleIndex",indexList);
            System.out.println("Index");}
        req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req,resp);
        return;
    }
}
