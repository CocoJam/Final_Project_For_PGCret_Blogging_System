package Article;

import Comment.Comments;
import Comment.CommentsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.conn;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This servlet is for displaying the actual individual article with its content.

public class ArticleServlet extends HttpServlet {
    private ArticlesDAO articlesDAO;
    private int ArticleID;
    private String ArticleName;
    private String ArticleContent;
    private Articles article;
    private HttpSession session;
    private List<Articles> indexList;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO;

    public List<Comments> gettingTheListOfComments(int articleID) {
        commentsDAO = new CommentsDAO();
        return listOfComments = commentsDAO.selectionComments(articleID);
    }

    //Grab everything that is related to the article, set sessions with the article content and comments list (AND ownership) and dispatch to comments servlet to get the comments.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editing = req.getParameter("edit");
        session = req.getSession();
        articlesDAO = new ArticlesDAO();
        try {
            ArticleID = Integer.parseInt(req.getParameter("acticleId"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        System.out.println(ArticleID + "articleid");
        article = articlesDAO.selectionArticles(ArticleID);
        System.out.println(article.getUsername() + "This is user");
        if (session.getAttribute("username") != null) {
            if (article.getUsername().equals(session.getAttribute("username"))) {
                article.setOwner(true);
            }
        }

        //The below is comments in the article.
        session.setAttribute("articleID", ArticleID);
        session.setAttribute("articleContents", article);
        listOfComments = gettingTheListOfComments(ArticleID);
        session.setAttribute("commentlist", listOfComments);
        try {
            System.out.println(conn.isClosed()+ " is this closed?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/Comments").forward(req, resp);
        return;
    }


    //doPost gets POST request to add or edit article depends on where the button was pushed (dependent on the parameter value).
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        articlesDAO = new ArticlesDAO();
        String addingArticles = req.getParameter("add");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (addingArticles != null) {

            //Scenario 1: When adding new article when pressed within the articleIndex.jsp.
            if (addingArticles.equals("addNewArticle")) {
                session.setAttribute("articleContents", null);
                req.setAttribute("add", null);
                session.setAttribute("Upload", "addNewArticle");
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
            } else if (addingArticles.equals("EditArticle")) {
                System.out.println("TRying to edit article");
                session.setAttribute("articleID", ArticleID);
                session.setAttribute("Upload", "ArticlesUpload");
                req.setAttribute("articleContents", article);
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //Scenario 3: Redirect from Article Creation page once editing complete.
                // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
            } else if (addingArticles.equals("Editted")) {
                ArticleName = req.getParameter("ArticleName");
                ArticleContent = req.getParameter("ArticleContent");
                article = articlesDAO.updateArticles(ArticleName, ArticleContent, ArticleID);
                req.setAttribute("articleContents", article);
                session.setAttribute("Upload", null);
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                return;
                //Scenario 4: Redirect from Article Creation page once creation of a new page is completed.
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

                // dispatching back into the articleIndex after finished creating new article and have uploaded the info to SQL via DAO
                // TODO whether to redirect to the new article page instead
                session.setAttribute("ArticleIndex", indexList);
                session.setAttribute("Upload", null);
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
                return;
            }
        }
    }
}
