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
    private String ArticleListStatus;

    //Hyperlink from the Profilepage.jsp has parameter called ArticleList and the value of the parameter will return ALL or SELF.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        username = (String) session.getAttribute("username");
        if (req.getParameter("articleList") != null) {
            session.setAttribute("ArticleListStatus", req.getParameter("articleList"));
        }
        ArticleListStatus = (String) session.getAttribute("ArticleListStatus");
        switchbetweenAllOrSelf(req, session, username);
        closingConnection();
        System.out.println("TESTA");
//        req.getRequestDispatcher("/Articles").include(req, resp);
        System.out.println("TESTB");

//        if (req.getParameter("profilePopulate") == null || !req.getParameter("profilePopulate").equals("yes")) {
            //This is when the navbar click to article index (either self or all) is clicked
            req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp); //testing
            System.out.println("TESTC");
            return;
//        }

//        else {
//            //This is when the display button is clicked to populate the user's article list on the profile-page.
//            req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").include(req, resp);
//            System.out.println("TESTD");
//            return;
//        }
    }

    //This: (1) determines whether to grab ALL or SELF (2) populate the list to be sent back
    private void switchbetweenAllOrSelf(HttpServletRequest req, HttpSession session, String username) {
        System.out.println("Checking self or all");
        if (ArticleListStatus != null) {

            if (ArticleListStatus.equals("self")) {
                System.out.println("self");
                session.setAttribute("articleList", "self");
                indexList = new ArticleListObjectDAO().selectionArticlesList(username);

                //testing forloop below
                for (Articles articles : indexList) {
                    System.out.println("CATEGORY !!!" + articles.getCategory());
                }
                checkingForOwnership(username, indexList);
                session.setAttribute("ArticleIndex", indexList);

            } else if (ArticleListStatus.equals("all")) {
                System.out.println("all");
                session.setAttribute("articleList", "all");
                indexList = new ArticleListObjectDAO().selectionAllArticlesList();
                checkingForOwnership(username, indexList);
                session.setAttribute("ArticleIndex", indexList);
            }
        }
    }

    //Using session username to check if the accessor is the owner, set Article .setOwner
    public static void checkingForOwnership(String username, List<Articles> indexList) {
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
