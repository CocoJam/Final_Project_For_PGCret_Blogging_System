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

import static Article.ArticlesIndexServlet.articleSetOwnership;
import static Article.ArticlesIndexServlet.checkingForOwnership;
import static Connection.ConnectionToTheDataBase.cookieLogOut;
import static Connection.ConnectionToTheDataBase.cookieTracker;

/**
 * Created by ljam763 on 25/05/2017.
 */

//This servlet is for displaying the actual individual article with its content.
public class ArticleServlet2 extends HttpServlet {

    public class innerclass {
        public void setArticlesDAO(ArticlesDAO articlesDAO) {
            this.articlesDAO = articlesDAO;
        }

        public void setArticleName(String articleName) {
            ArticleName = articleName;
        }

        public void setArticleContent(String articleContent) {
            ArticleContent = articleContent;
        }

        public void setArticleCategory(String articleCategory) {
            this.articleCategory = articleCategory;
        }

        public void setArticle(Articles article) {
            this.article = article;
        }

        public void setSession(HttpSession session) {
            this.session = session;
        }

        public void setIndexList(List<Articles> indexList) {
            this.indexList = indexList;
        }

        public void setListOfComments(List<Comments> listOfComments) {
            this.listOfComments = listOfComments;
        }

        public void setCommentsDAO(CommentsDAO commentsDAO) {
            this.commentsDAO = commentsDAO;
        }

        private ArticlesDAO articlesDAO;
        private String ArticleName;
        private String ArticleContent;
        private String articleCategory;
        private Articles article = null;
        private HttpSession session;
        private List<Articles> indexList;
        private List<Comments> listOfComments;
        private CommentsDAO commentsDAO;

        public void setArticleID(int articleID) {
            ArticleID = articleID;
        }

        private int ArticleID;
    }



    //Getting the list of the comments assoicated within the specific article using article ID.
    public List<Comments> gettingTheListOfComments(int articleID, innerclass innerclass) {
        innerclass.setCommentsDAO(new CommentsDAO());
        return innerclass.commentsDAO.selectionComments(articleID);
    }

    //Grab everything that is related to the article, set sessions with the article content and comments list (AND ownership) and dispatch to comments servlet to get the comments.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        try {
            innerclass innerclass = new innerclass();
//        This is when the create new article button is clicked on the navbar it forwards to the relevant Post method.
            
            if (req.getParameter("add") != null) {
                addNewArticle(req, resp);
                return;
            }
            innerclass.setSession(req.getSession());
            innerclass.setArticlesDAO(new ArticlesDAO());
            int ArticleID = 0;
            ArticleID = Integer.parseInt(req.getParameter("acticleId"));
            ArticleID = getArticleBasedOnId(req, resp, innerclass, ArticleID);
            //This is viewing the Article
            innerclass.session.setAttribute("articleList", "self");
            if (innerclass.article != null) {
                if (innerclass.session.getAttribute("username") != null) {
                    if (innerclass.article.getUsername().equals(innerclass.session.getAttribute("username"))) {
                        innerclass.article.setOwner(true);
                    }
                }
                //The below is comments in the article.
                innerclass.session.setAttribute("articleID", ArticleID);
                innerclass.session.setAttribute("articleContents", innerclass.article);
                innerclass.listOfComments = gettingTheListOfComments(ArticleID, innerclass);
                innerclass.session.setAttribute("commentlist", innerclass.listOfComments);
                //Dispatching the article and comments.
                req.getRequestDispatcher("Comments").include(req, resp);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cookieTracker(req, resp);
        return;
    }

    //Getting the article based on the Article Id.
    private int getArticleBasedOnId(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, int ArticleID) throws SQLException, NullPointerException {
        innerclass.setArticle(innerclass.articlesDAO.selectionArticles(ArticleID));
        innerclass.article.setLikeNumber(innerclass.articlesDAO.NumberLike(ArticleID));
        innerclass.article.setLiked(innerclass.articlesDAO.Liked((String) innerclass.session.getAttribute("username"), ArticleID));
        return ArticleID;
    }

    //Called when the new Article is intended to be made, so bounce to the dopost for serving back to the Article Creation page.
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
        try {
            innerclass innerclass = new innerclass();
            innerclass.setArticlesDAO(new ArticlesDAO());
            String addingArticles = req.getParameter("add");
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute("username");
            if (addingArticles != null) {
                //Scenario 1: When adding new article when pressed within the articleIndex.jsp.
                if (addingArticles.equals("addNewArticle")) {
                    EnteringToAddNewArticle(req, resp, session);
                    return;
                    //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
                } else if (addingArticles.equals("EditArticle")) {
                    EnteringToEditArticle(req, resp, innerclass, session);
                    return;
                    //Scenario 3: Redirect from Article Creation page once editing complete.
                    // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
                } else if (addingArticles.equals("Editted")) {
                    FinishedEdittingArticle(req, resp, innerclass, session, username);
                    return;
                    //Scenario 4: Redirect from Article Creation page once creation of a new page is completed.
                } else if (addingArticles.equals("addingToDataBase")) {
                    FinishingAddingNewArticle(req, resp, innerclass, session, username);
                    return;
                }
            }
            if (req.getParameter("like") != null) {
                if (LikeSystem(req, resp, innerclass, session)) return;
            }
            doGet(req, resp);
        } catch (Exception e) {
            cookieTracker(req, resp);
        }
        return;
    }

    //The like System check is the article liked or not then give proper responds
    private boolean LikeSystem(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, HttpSession session) throws IOException {
        if (req.getParameter("like").equals("like")) {
            if (req.getParameter("articleIdnumber") != null && req.getParameter("likepeople") != null) {
                boolean like = innerclass.articlesDAO.updateLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                if (!like) {
                    innerclass.articlesDAO.deleteLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                }
                resp.getWriter().print(innerclass.articlesDAO.NumberLike(Integer.parseInt(req.getParameter("articleIdnumber"))));
                return true;
            }
        }
        return false;
    }

    //Step 1. for adding new Article and directing the user to Article Creation page
    private void EnteringToAddNewArticle(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        doPostAddNewArticle(req, session);
        req.getRequestDispatcher("WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
    }

    //Step 2. for adding New article, which add Article to the database then dispatches user to ArticleIndex.
    private void FinishingAddingNewArticle(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, HttpSession session, String username) throws ServletException, IOException {
        gettingContentFromJsp(req, innerclass);
        innerclass.articlesDAO.madeArticles(innerclass.ArticleName, innerclass.articleCategory, username, innerclass.ArticleContent);
        String Listformation = (String) session.getAttribute("articleList");
        RequiredListAllOrSelf(username, Listformation, innerclass);
        // dispatching back into the articleIndex after finished creating new article and have uploaded the info to SQL via DAO
        session.setAttribute("ArticleIndex", innerclass.indexList);
        session.setAttribute("Upload", null);
        req.getRequestDispatcher("WEB-INF/webthings/ArticleIndex.jsp").forward(req, resp);
    }

    //Entering the Article Editing page, which is the Article creation page.
    private void EnteringToEditArticle(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, HttpSession session) throws ServletException, IOException {
        gettingContentFromJsp(req, innerclass);
        doPostEnteringEditArticle(session, innerclass);
        req.getRequestDispatcher("WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
    }

    //Step 2. for Editing article, which update this Article to the database then dispatches user to Article.
    private void FinishedEdittingArticle(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, HttpSession session, String username) throws ServletException, IOException {
        gettingContentFromJsp(req, innerclass);
        Articles article = null;
        try {
            article = innerclass.articlesDAO.updateArticles(innerclass.ArticleName, innerclass.articleCategory, innerclass.ArticleContent, innerclass.ArticleID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("articleList", "self");
        session.setAttribute("articleContents", article);
        session.setAttribute("Upload", null);
        checkingForOwnershipArticle(username, article);
        req.getRequestDispatcher("WEB-INF/webthings/Article.jsp").forward(req, resp);
    }



//Using to check is the current state of article index veiwing to be self or all articles.
    private void RequiredListAllOrSelf(String username, String listformation, innerclass innerclass) {
        if (listformation.equals("all")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionAllArticlesList());
            checkingForOwnership(username, innerclass.indexList);
        } else if (listformation.equals("self")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionArticlesList(username));
            checkingForOwnership(username, innerclass.indexList);
        }
    }

    //Getting all content from the JSP of the article page.
    private void gettingContentFromJsp(HttpServletRequest req, innerclass innerclass) {
        innerclass.setArticleName(req.getParameter("ArticleName"));
        innerclass.setArticleContent(req.getParameter("ArticleContent"));
        innerclass.setArticleCategory(req.getParameter("ArticleCategory"));
        if (req.getParameter("articleidnumber") != null) {
            try {
                innerclass.setArticleID(Integer.parseInt(req.getParameter("articleidnumber")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    // Setting the session Attribute of the Article situation. For editing article
    private void doPostEnteringEditArticle(HttpSession session, innerclass innerclass) {
        session.setAttribute("articleList", "self");
        session.setAttribute("articleID", innerclass.ArticleID);
        session.setAttribute("Upload", "ArticlesUpload");
        try {
            session.setAttribute("articleContents", innerclass.articlesDAO.selectionArticles(innerclass.ArticleID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Setting the session Attribute of the Article situation. For adding article
    private void doPostAddNewArticle(HttpServletRequest req, HttpSession session) {
        session.setAttribute("articleList", "self");
        session.setAttribute("articleContents", null);
        req.setAttribute("add", null);
        session.setAttribute("Upload", "addNewArticle");
    }
//Checking for this specific article's ownership.
    public void checkingForOwnershipArticle(String username, Articles article) {
        articleSetOwnership(username, article);
    }
}