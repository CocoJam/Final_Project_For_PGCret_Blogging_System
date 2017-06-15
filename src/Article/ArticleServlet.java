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

    public class innerclass {
        public ArticlesDAO getArticlesDAO() {
            return articlesDAO;
        }

        public void setArticlesDAO(ArticlesDAO articlesDAO) {
            this.articlesDAO = articlesDAO;
        }

        public String getArticleName() {
            return ArticleName;
        }

        public void setArticleName(String articleName) {
            ArticleName = articleName;
        }

        public String getArticleContent() {
            return ArticleContent;
        }

        public void setArticleContent(String articleContent) {
            ArticleContent = articleContent;
        }

        public String getArticleCategory() {
            return articleCategory;
        }

        public void setArticleCategory(String articleCategory) {
            this.articleCategory = articleCategory;
        }

        public Articles getArticle() {
            return article;
        }

        public void setArticle(Articles article) {
            this.article = article;
        }

        public HttpSession getSession() {
            return session;
        }

        public void setSession(HttpSession session) {
            this.session = session;
        }

        public List<Articles> getIndexList() {
            return indexList;
        }

        public void setIndexList(List<Articles> indexList) {
            this.indexList = indexList;
        }

        public List<Comments> getListOfComments() {
            return listOfComments;
        }

        public void setListOfComments(List<Comments> listOfComments) {
            this.listOfComments = listOfComments;
        }

        public CommentsDAO getCommentsDAO() {
            return commentsDAO;
        }

        public void setCommentsDAO(CommentsDAO commentsDAO) {
            this.commentsDAO = commentsDAO;
        }

        private ArticlesDAO articlesDAO;
        private String ArticleName;
        private String ArticleContent;

        public int getArticleId() {
            return ArticleId;
        }

        public void setArticleId(int articleId) {
            ArticleId = articleId;
        }

        private int ArticleId;
        private String articleCategory;
        private Articles article = null;
        private HttpSession session;
        private List<Articles> indexList;
        private List<Comments> listOfComments;
        private CommentsDAO commentsDAO;
    }
//    private ArticlesDAO articlesDAO;
//    private String ArticleName;
//    private String ArticleContent;
//    private String articleCategory;
//    private Articles article = null;
//    private HttpSession session;
//    private List<Articles> indexList;
//    private List<Comments> listOfComments;
//    private CommentsDAO commentsDAO;

    //Getting the list of the comments assoicated within the specific article using article ID.
    public List<Comments> gettingTheListOfComments(int articleID) {
        CommentsDAO commentsDAO = new CommentsDAO();
        return commentsDAO.selectionComments(articleID);
    }

    //Grab everything that is related to the article, set sessions with the article content and comments list (AND ownership) and dispatch to comments servlet to get the comments.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        try {
            innerclass innerclass = new innerclass();
            HttpSession session = req.getSession();
            innerclass.setSession(session);
//        This is when the create new article button is clicked on the navbar it forwards to the relevant Post method.
            System.out.println("Creating new article from Navbar");
            if (req.getParameter("add") != null) {
                addNewArticle(req, resp);
                return;
            }
//        session = req.getSession();
            ArticlesDAO articlesDAO = new ArticlesDAO();
            innerclass.setArticlesDAO(articlesDAO);
            int ArticleID = 0;
            try {
                ArticleID = getArticleBasedOnId(req, resp, innerclass);
            } catch (NumberFormatException e) {
                cookieTracker(req, resp);
                return;
            } catch (SQLException e) {
                cookieTracker(req, resp);
                return;
            }
            //This is viewing the Article
            session.setAttribute("articleList", "self");
            if (innerclass.getArticle() != null) {
                if (session.getAttribute("username") != null) {
                    if (innerclass.getArticle().getUsername().equals(session.getAttribute("username"))) {
                        innerclass.getArticle().setOwner(true);
                    }
                }
                //The below is comments in the article.
                session.setAttribute("articleID", ArticleID);
                session.setAttribute("articleContents", innerclass.getArticle());
                List<Comments> listOfComments = gettingTheListOfComments(innerclass.getArticle().getArticleid());
                session.setAttribute("commentlist", listOfComments);
                //Dispatching the article and comments.
                System.out.println("selected articles");
                req.getRequestDispatcher("/Comments").include(req, resp);
                return;
            }
            cookieTracker(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
        return;
    }

    private int getArticleBasedOnId(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) throws SQLException {
        int ArticleID = 0;
        try {
            ArticleID = Integer.parseInt(req.getParameter("acticleId"));
            System.out.println(ArticleID + "articleid");
            System.out.println(innerclass.getArticlesDAO());
            Articles article = innerclass.getArticlesDAO().selectionArticles(ArticleID);
            article.setLikeNumber(innerclass.getArticlesDAO().NumberLike(article.getArticleid()));
            article.setLiked(innerclass.getArticlesDAO().Liked((String) innerclass.getSession().getAttribute("username"), article.getArticleid()));
            innerclass.setArticle(article);
        } catch (Exception e) {
            e.getStackTrace();
            cookieTracker(req, resp);
        }
        return innerclass.getArticle().getArticleid();
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
        try {
            cookieLogOut(req, resp);
            ArticlesDAO articlesDAO = new ArticlesDAO();
            String addingArticles = req.getParameter("add");
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute("username");
            innerclass innerclass = new innerclass();
            innerclass.setArticlesDAO(articlesDAO);
            if (addingArticles != null) {
                //Scenario 1: When adding new article when pressed within the articleIndex.jsp.
                if (addingArticles.equals("addNewArticle")) {
                    doPostAddNewArticle(req, session);
                    req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                    return;
                    //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
                } else if (addingArticles.equals("EditArticle")) {
//                gettingContentFromJsp(req,innerclass);
                    int articlenumber = 0;
                    articlenumber = Integer.parseInt(req.getParameter("articleidnumber"));
                    innerclass.setArticleId(articlenumber);
                    System.out.println("here is the innerclass article number " + articlenumber);
                    doPostEnteringEditArticle(req, resp, session, articlenumber);
                    req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                    return;

                    //Scenario 3: Redirect from Article Creation page once editing complete.
                    // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
                } else if (addingArticles.equals("Editted")) {
                    gettingContentFromJsp(req, resp, innerclass);
                    Articles article = null;
                    try {
//                    article = articlesDAO.updateArticles(ArticleName, articleCategory, ArticleContent, article.getArticleid());
                        article = innerclass.articlesDAO.updateArticles(innerclass.ArticleName, innerclass.articleCategory, innerclass.ArticleContent, innerclass.ArticleId);
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
                    gettingContentFromJsp(req, resp, innerclass);
                    innerclass.articlesDAO.madeArticles(innerclass.ArticleName, innerclass.articleCategory, username, innerclass.ArticleContent);
                    String Listformation = (String) session.getAttribute("articleList");
                    RequiredListAllOrSelf(username, Listformation, innerclass);
                    // dispatching back into the articleIndex after finished creating new article and have uploaded the info to SQL via DAO
                    session.setAttribute("ArticleIndex", innerclass.indexList);
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
                        boolean like = innerclass.articlesDAO.updateLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                        if (!like) {
                            System.out.println("delete like");
                            innerclass.articlesDAO.deleteLike((String) session.getAttribute("username"), Integer.parseInt(req.getParameter("articleIdnumber")));
                        }
                        System.out.println(innerclass.articlesDAO.NumberLike(Integer.parseInt(req.getParameter("articleIdnumber"))));
                        resp.getWriter().print(innerclass.articlesDAO.NumberLike(Integer.parseInt(req.getParameter("articleIdnumber"))));
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            cookieTracker(req, resp);
        }
        doGet(req, resp);
        return;
    }

    private void RequiredListAllOrSelf(String username, String listformation, innerclass innerclass) {
        if (listformation.equals("all")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionAllArticlesList());
            checkingForOwnership(username, innerclass.getIndexList());
        } else if (listformation.equals("self")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionArticlesList(username));
            checkingForOwnership(username, innerclass.getIndexList());
        }
    }

    private innerclass gettingContentFromJsp(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) {
        try {
            innerclass.setArticleName(req.getParameter("ArticleName"));
//        ArticleName = req.getParameter("ArticleName");
            innerclass.setArticleContent(req.getParameter("ArticleContent"));
//        ArticleContent = req.getParameter("ArticleContent");
            innerclass.setArticleCategory(req.getParameter("ArticleCategory"));
//        articleCategory = req.getParameter("ArticleCategory");
            int articlenumber = 0;
            HttpSession session = req.getSession();
            articlenumber = (int) session.getAttribute("articleID");
            System.out.println("inner class aricle id" + articlenumber);
            innerclass.setArticleId(articlenumber);
        } catch (Exception e) {
            e.getStackTrace();
            cookieTracker(req, resp);
        }
        return innerclass;
    }

    private void doPostEnteringEditArticle(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int articlenumber) {
        Articles article = null;
        try {
            session.setAttribute("articleList", "self");
            session.setAttribute("articleID", articlenumber);
            session.setAttribute("Upload", "ArticlesUpload");
            ArticlesDAO articlesDAO = new ArticlesDAO();
            article = articlesDAO.selectionArticles(articlenumber);
            System.out.println("Entering the editting page with " + articlenumber);
            session.setAttribute("articleContents", article);
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
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

//    public void checkingForOwnershipArticle(String username, Articles articles) {
//        if (articles.getUsername().equals(username)) {
//            System.out.println("yes");
//            articles.setOwner(true);
//        } else {
//            System.out.println("No");
//            articles.setOwner(false);
//        }
//    }

}
