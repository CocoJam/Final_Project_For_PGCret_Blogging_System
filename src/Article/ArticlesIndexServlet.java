package Article;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.cookieLogOut;
import static Connection.ConnectionToTheDataBase.cookieTracker;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ArticlesIndexServlet extends HttpServlet {
    public class innerclass {
        private List<Articles> indexList;

        public List<Articles> getIndexList() {
            return indexList;
        }

        public void setIndexList(List<Articles> indexList) {
            this.indexList = indexList;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getArticleListStatus() {
            return ArticleListStatus;
        }

        public void setArticleListStatus(String articleListStatus) {
            ArticleListStatus = articleListStatus;
        }

        private String username;
        private String ArticleListStatus;
    }

//    private List<Articles> indexList;
//    private String username;
//    private String ArticleListStatus;

    //Hyperlink from the Profilepage.jsp has parameter called ArticleList and the value of the parameter will return ALL or SELF.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            cookieLogOut(req, resp);
            HttpSession session = req.getSession();
            innerclass innerclass = new innerclass();
            innerclass.setUsername((String) session.getAttribute("username"));
//        username = (String) session.getAttribute("username");
            if (req.getParameter("articleList") != null) {
                session.setAttribute("ArticleListStatus", req.getParameter("articleList"));
                innerclass.setArticleListStatus((String) session.getAttribute("ArticleListStatus"));
//            ArticleListStatus = (String) session.getAttribute("ArticleListStatus");
                switchbetweenAllOrSelf(req, resp, session, innerclass.username, innerclass);
                req.getRequestDispatcher("${contextPath}/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp); //testing
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
        cookieTracker(req, resp);
        return;
    }

    //This: (1) determines whether to grab ALL or SELF (2) populate the list to be sent back
    private void switchbetweenAllOrSelf(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String username, innerclass innerclass) {
        System.out.println("Checking self or all");
        try {
            if (innerclass.ArticleListStatus != null) {
                if (innerclass.ArticleListStatus.equals("self")) {
                    System.out.println("self");
                    session.setAttribute("articleList", "self");
                    innerclass.setIndexList(new ArticleListObjectDAO().selectionArticlesList(username));
//                indexList = new ArticleListObjectDAO().selectionArticlesList(username);
                    checkingForOwnership(username, innerclass.indexList);
                    session.setAttribute("ArticleIndex", innerclass.indexList);
                } else if (innerclass.ArticleListStatus.equals("all")) {
                    session.setAttribute("articleList", "all");
                    innerclass.setIndexList(new ArticleListObjectDAO().selectionAllArticlesList());
//                indexList = new ArticleListObjectDAO().selectionAllArticlesList();
                    checkingForOwnership(username, innerclass.indexList);
                    session.setAttribute("ArticleIndex", innerclass.indexList);
                }
            } else {
                cookieTracker(req, resp);
            }
        } catch (Exception e){
            cookieTracker(req, resp);
        }

    }

    //Using session username to check if the accessor is the owner, set Article .setOwner
    public static void checkingForOwnership(String username, List<Articles> indexList) {
        for (Articles articles : indexList) {
            articleSetOwnership(username, articles);
        }
    }

    public static void articleSetOwnership(String username, Articles articles) {
        if (articles.getUsername().equals(username)) {
            System.out.println("yes");
            articles.setOwner(true);
        } else {
            System.out.println("No");
            articles.setOwner(false);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        doGet(req, resp);
    }
}
