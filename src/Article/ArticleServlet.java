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

import static Article.ArticlesIndexServlet.checkingForOwnership;
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
    private String articleCategory;
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

//        This is when the create new article button is clicked on the navbar it forwards to the relevant Post method.
        System.out.println("Creating new article from Navbar");

        if (req.getParameter("add") != null){
            if (req.getParameter("add").equals("addNewArticle")){
                System.out.println(req.getParameter("add"));
                doPost(req,resp);
                return;
            }
        }

        String editing = req.getParameter("edit");
        session = req.getSession();
        articlesDAO = new ArticlesDAO();
        try {
            ArticleID = Integer.parseInt(req.getParameter("acticleId"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        //This is viewing the Article
        System.out.println(ArticleID + "articleid");
        article = articlesDAO.selectionArticles(ArticleID);
        session.setAttribute("articleList", "self");
        if (article!= null){
        System.out.println(article.getUsername() + ": This is user");
            System.out.println(article.getCategory() + ": This is the category");
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

        //Dispatching the article and comments.
        try {
            System.out.println(conn.isClosed() + " is this closed?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/Comments").include(req, resp);}
        else{
            req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
        }
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
                session.setAttribute("articleList", "self");
                session.setAttribute("articleContents", null);
                req.setAttribute("add", null);
                session.setAttribute("Upload", "addNewArticle");
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
            } else if (addingArticles.equals("EditArticle")) {
                System.out.println("TRying to edit article");
                session.setAttribute("articleList", "self");
                session.setAttribute("articleID", ArticleID);
                session.setAttribute("Upload", "ArticlesUpload");
                session.setAttribute("articleContents", article);
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //Scenario 3: Redirect from Article Creation page once editing complete.
                // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
            } else if (addingArticles.equals("Editted")) {
                ArticleName = req.getParameter("ArticleName");
                ArticleContent = req.getParameter("ArticleContent");
                articleCategory = req.getParameter("ArticleCategory");
                article = articlesDAO.updateArticles(ArticleName, articleCategory, ArticleContent, ArticleID);
                session.setAttribute("articleList", "self");
                session.setAttribute("articleContents", article);
                session.setAttribute("Upload", null);
                checkingForOwnershipArticle(username,article);
                closingConnection();
                req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                return;
                //Scenario 4: Redirect from Article Creation page once creation of a new page is completed.
            } else if (addingArticles.equals("addingToDataBase")) {
                System.out.println(username);
                String ArticleName = req.getParameter("ArticleName");
                String ArticleContent = req.getParameter("ArticleContent");
                String articleCategory = req.getParameter("ArticleCategory");
                articlesDAO.madeArticles(ArticleName, articleCategory, username, ArticleContent);
                String Listformation = (String) session.getAttribute("articleList");
                System.out.println((String) session.getAttribute("articleList"));
                if (Listformation.equals("all")) {
                    indexList = new ArticleListObjectDAO().selectionAllArticlesList();
                    checkingForOwnership(username, indexList);
                } else if (Listformation.equals("self")) {
                    indexList = new ArticleListObjectDAO().selectionArticlesList(username);
                    checkingForOwnership(username, indexList);
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
        doGet(req,resp);
        return;
    }

    public void checkingForOwnershipArticle(String username, Articles article) {
        if (article.getUsername().equals(username)) {
            System.out.println("yes");
            article.setOwner(true);
        } else {
            System.out.println("No");
            article.setOwner(false);
        }
    }
}
