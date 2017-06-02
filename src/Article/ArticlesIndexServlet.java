package Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Connection.ConnectionToTheDataBase.closingConnection;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesIndexServlet extends HttpServlet {
    private List<Articles> indexList;
    private String username;

    //Hyperlink from the Profilepage.jsp has parameter called ArticleList and the value of the parameter will return ALL or SELF.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        switchbetweenAllOrSelf(req, session, username);
        closingConnection();
        req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
        return;
    }

    //This: (1) determines whether to grab ALL or SELF (2) populate the list to be sent back
    private void switchbetweenAllOrSelf(HttpServletRequest req, HttpSession session, String username) {
        if (req.getParameter("articleList") != null) {
            if (req.getParameter("articleList").equals("self")) {
                System.out.println("self");
                session.setAttribute("articleList", "self");
                indexList = new ArticleListObjectDAO().selectionArticlesList(username);
                checkingForOwnership(username);
                session.setAttribute("ArticleIndex", indexList);
            } else if (req.getParameter("articleList").equals("all")) {
                System.out.println("all");
                session.setAttribute("articleList", "all");
                indexList = new ArticleListObjectDAO().selectionAllArticlesList();
                checkingForOwnership(username);
                session.setAttribute("ArticleIndex", indexList);
            }
        }
    }
    //Using session username to check if the accessor is the owner, set Article .setOwner
    private void checkingForOwnership(String username) {
        for (Articles articles : indexList) {
            if (articles.getUsername().equals(username)) {
                System.out.println("yes");
                articles.setOwner(true);
            } else {
                System.out.println("No");
                articles.setOwner(false);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
