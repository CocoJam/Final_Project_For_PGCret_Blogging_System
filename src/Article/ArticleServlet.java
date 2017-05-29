package Article;

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

//0. This is a servlet which uses the Articles + ArticlesDAO classes to allow users to interact with the DB of articles. It is the mechanism by which the JSP pages are created.

public class ArticleServlet extends HttpServlet {
    private ArticlesDAO articlesDAO = new ArticlesDAO();
    private int ArticleID;
    private String ArticleName;
    private String ArticleContent;
    private Articles article;
    private HttpSession session;
    private List<Articles> indexList;


    //1. using GET request from the Article.jsp to display an article according to the session of the user (currently unused, no attributes set for the dispatcher to the JSP, discuss with James about intentions for this and whether this is required).
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editing = req.getParameter("edit");
        session = req.getSession();
        ArticleID = Integer.parseInt(req.getParameter("articleID"));
        article = articlesDAO.selectionArticles(ArticleID);
        session.setAttribute("articleID", ArticleID); //is session the right place to put this? It may be more wise to specifically place as part of req.setAttribute
        session.setAttribute("articleContents", article);
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        return;
    }


    //2. using the POST method to add new article and edit existing article.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String addingArticles = req.getParameter("add");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (addingArticles != null) {
            if (addingArticles.equals("addNewArticle")) {
                session.setAttribute("articleContents", null);
                req.setAttribute("add", null);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //edit the existing article (need to check with James as this has yet to be created)
            } else if (addingArticles.equals("EditArticle")) {
                session.setAttribute("articleID", ArticleID);
                req.setAttribute("articleContents", article);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //When you submit the edited version of the article when finished editing (This is currently not available).
            } else if (addingArticles.equals("Editted")) {
                ArticleName = req.getParameter("ArticleName");
                ArticleContent = req.getParameter("ArticleContent"); //where is this in the JSP?
                article = articlesDAO.updataArticles(ArticleName, ArticleContent, ArticleID);
                req.setAttribute("articleContents", article);
                req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                return;


                //This is adding a new article (typed article) back up to the database, same time as grabbing attributes and inserting them into SQL.
            } else if (addingArticles.equals("addingToDataBase")) {
                System.out.println(username);
                String ArticleName = req.getParameter("ArticleName");
                String ArticleContent = req.getParameter("ArticleContent");
                articlesDAO.madeArticles(ArticleName, username, ArticleContent);
                String Listformation = (String) session.getAttribute("articleList");
                System.out.println((String) session.getAttribute("articleList"));

                if (Listformation.equals("all")) {
                    indexList = new ArticleListObjectDAO().selectionAllArticlesList();
                } else if (Listformation.equals("self")) {
                    indexList = new ArticleListObjectDAO().selectionArticlesList(username);
                }

                session.setAttribute("ArticleIndex", indexList);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
                return;

            }
        }
    }
}
