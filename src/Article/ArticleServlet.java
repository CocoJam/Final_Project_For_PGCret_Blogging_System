package Article;

import Comment.Comments;
import Comment.CommentsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static Article.ArticlesIndexServlet.articleSetOwnership;
import static Article.ArticlesIndexServlet.checkingForOwnership;
import static Connection.ConnectionToTheDataBase.*;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This servlet is for displaying the actual individual article with its content.
public class ArticleServlet extends HttpServlet {
    private ArticlesDAO articlesDAO;
    private String ArticleName;
    private String ArticleContent;
    private String articleCategory;
    private Articles article = null;
    private HttpSession session;
    private List<Articles> indexList;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO;

    //Getting the list of the comments assoicated within the specific article using article ID.
    public List<Comments> gettingTheListOfComments(int articleID) {
        commentsDAO = new CommentsDAO();
        return listOfComments = commentsDAO.selectionComments(articleID);
    }

    //Grab everything that is related to the article, set sessions with the article content and comments list (AND ownership) and dispatch to comments servlet to get the comments.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
//        This is when the create new article button is clicked on the navbar it forwards to the relevant Post method.
        System.out.println("Creating new article from Navbar");
        if (req.getParameter("add") != null) {
            addNewArticle(req, resp);
            return;
        }
        session = req.getSession();
        articlesDAO = new ArticlesDAO();
        int ArticleID = 0;
        try {
            ArticleID = getArticleBasedOnId(req);
        } catch (NumberFormatException e) {
            cookieTracker(req, resp);
            return;
        } catch (SQLException e) {
            cookieTracker(req, resp);
            return;
        }
        //This is viewing the Article
        session.setAttribute("articleList", "self");
        if (article != null) {
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
            System.out.println("selected articles");
            req.getRequestDispatcher("/Comments").include(req, resp);
            return;
        }
        cookieTracker(req, resp);
        return;
    }

    private int getArticleBasedOnId(HttpServletRequest req) throws SQLException {
        int ArticleID;
        ArticleID = Integer.parseInt(req.getParameter("acticleId"));
        System.out.println(ArticleID + "articleid");
        article = articlesDAO.selectionArticles(ArticleID);
        article.setLikeNumber(articlesDAO.NumberLike(article.getArticleid()));
        article.setLiked(articlesDAO.Liked((String) session.getAttribute("username"),article.getArticleid()));
        return ArticleID;
    }

    private void addNewArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add").equals("addNewArticle")) {
            doPost(req, resp);
            return;
        } else {
            cookieTracker(req, resp);
            return;
        }
    }


    //doPost gets POST request to add or edit article depends on where the button was pushed (dependent on the parameter value).
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        articlesDAO = new ArticlesDAO();
        String addingArticles = req.getParameter("add");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (addingArticles != null) {

            //Scenario 1: When adding new article when pressed within the articleIndex.jsp.
            if (addingArticles.equals("addNewArticle")) {
                doPostAddNewArticle(req, session);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;
                //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
            } else if (addingArticles.equals("EditArticle")) {
                doPostEnteringEditArticle(session);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;

                //Scenario 3: Redirect from Article Creation page once editing complete.
                // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
            } else if (addingArticles.equals("Editted")) {
                gettingContentFromJsp(req);
                try {
                    article = articlesDAO.updateArticles(ArticleName, articleCategory, ArticleContent, article.getArticleid());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("articleList", "self");
                session.setAttribute("articleContents", article);
                session.setAttribute("Upload", null);
                checkingForOwnershipArticle(username, article);
                req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                return;
                //Scenario 4: Redirect from Article Creation page once creation of a new page is completed.
            } else if (addingArticles.equals("addingToDataBase")) {
                gettingContentFromJsp(req);
                articlesDAO.madeArticles(ArticleName, articleCategory, username, ArticleContent);
                String Listformation = (String) session.getAttribute("articleList");
                RequiredListAllOrSelf(username, Listformation);
                // dispatching back into the articleIndex after finished creating new article and have uploaded the info to SQL via DAO
                session.setAttribute("ArticleIndex", indexList);
                session.setAttribute("Upload", null);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
                return;
            }
        }
        if (req.getParameter("like") != null) {
            System.out.println("here is like");
            if (req.getParameter("like").equals("like")) {
                if (req.getParameter("articleIdnumber") != null && req.getParameter("likepeople") != null) {
                    System.out.println(req.getParameter("likepeople"));
                    System.out.println(req.getParameter("articleIdnumber"));
                    System.out.println(Integer.parseInt(req.getParameter("articleIdnumber")));
                   boolean like = articlesDAO.updateLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                   if (!like){
                       System.out.println("delete like");
                       articlesDAO.deleteLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                   }
                    System.out.println(articlesDAO.NumberLike(Integer.parseInt(req.getParameter("articleIdnumber"))));
                    resp.getWriter().print(articlesDAO.NumberLike(Integer.parseInt(req.getParameter("articleIdnumber"))));
                   return;
                }
            }
//            else if (req.getParameter("like").equals("unlike")) {
//                if (req.getParameter("likenumber") != null && Integer.parseInt(req.getParameter("likenumber")) > 0 && req.getParameter("articleIdnumber") != null) {
//                    System.out.println(Integer.parseInt(req.getParameter("likenumber") + 1));
//                    System.out.println(Integer.parseInt(req.getParameter("articleIdnumber")));
//                    articlesDAO.updateLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
//                    resp.getWriter().print(Integer.parseInt(req.getParameter("likenumber") )-1);
//                }
//            }
        }
        doGet(req, resp);
        return;
    }

    private void RequiredListAllOrSelf(String username, String listformation) {
        if (listformation.equals("all")) {
            indexList = new ArticleListObjectDAO().selectionAllArticlesList();
            checkingForOwnership(username, indexList);
        } else if (listformation.equals("self")) {
            indexList = new ArticleListObjectDAO().selectionArticlesList(username);
            checkingForOwnership(username, indexList);
        }
    }

    private void gettingContentFromJsp(HttpServletRequest req) {
        ArticleName = req.getParameter("ArticleName");
        ArticleContent = req.getParameter("ArticleContent");
        articleCategory = req.getParameter("ArticleCategory");
    }

    private void doPostEnteringEditArticle(HttpSession session) {
        session.setAttribute("articleList", "self");
        session.setAttribute("articleID", article.getArticleid());
        session.setAttribute("Upload", "ArticlesUpload");
        session.setAttribute("articleContents", article);
    }

    private void doPostAddNewArticle(HttpServletRequest req, HttpSession session) {
        session.setAttribute("articleList", "self");
        session.setAttribute("articleContents", null);
        req.setAttribute("add", null);
        session.setAttribute("Upload", "addNewArticle");
    }

    public void checkingForOwnershipArticle(String username, Articles article) {
        articleSetOwnership(username, article);
    }
}
