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
            System.out.println("Creating new article from Navbar");
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
                System.out.println("selected articles");
                req.getRequestDispatcher("/Comments").include(req, resp);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cookieTracker(req, resp);
        return;
    }

    private int getArticleBasedOnId(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass, int ArticleID) throws SQLException, NullPointerException {
        System.out.println(ArticleID + "articleid");
        Articles articles = innerclass.articlesDAO.selectionArticles(ArticleID);
        System.out.println(articles);
        innerclass.setArticle(innerclass.articlesDAO.selectionArticles(ArticleID));
        innerclass.article.setLikeNumber(innerclass.articlesDAO.NumberLike(ArticleID));
        innerclass.article.setLiked(innerclass.articlesDAO.Liked((String) innerclass.session.getAttribute("username"), ArticleID));
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
        try {
            innerclass innerclass = new innerclass();
            innerclass.setArticlesDAO(new ArticlesDAO());
            String addingArticles = req.getParameter("add");
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute("username");
            System.out.println(req.getParameter("ArticleName"));
            System.out.println(req.getParameter("articleidnumber"));
            if (addingArticles != null) {
                //Scenario 1: When adding new article when pressed within the articleIndex.jsp.
                if (addingArticles.equals("addNewArticle")) {
                    doPostAddNewArticle(req, session);
                    req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                    return;
                    //Scenario 2: Edit inside of your own article, therefore setting ownership is important. Dispatches to the ArticleCreationPage.jsp (but in editing mode).
                } else if (addingArticles.equals("EditArticle")) {
                    gettingContentFromJsp(req, innerclass);
                    doPostEnteringEditArticle(session, innerclass);
                    req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                    return;
                    //Scenario 3: Redirect from Article Creation page once editing complete.
                    // This is when you have finished editing the article and redirecting back to the article page from the article creation page, and then update that SQL the article details.
                } else if (addingArticles.equals("Editted")) {
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
                    req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                    return;
                    //Scenario 4: Redirect from Article Creation page once creation of a new page is completed.
                } else if (addingArticles.equals("addingToDataBase")) {
                    gettingContentFromJsp(req, innerclass);
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
            doGet(req, resp);
        } catch (Exception e) {
            cookieTracker(req, resp);
        }
        return;
    }

    private void RequiredListAllOrSelf(String username, String listformation, innerclass innerclass) {
        if (listformation.equals("all")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionAllArticlesList());
            checkingForOwnership(username, innerclass.indexList);
        } else if (listformation.equals("self")) {
            innerclass.setIndexList(new ArticleListObjectDAO().selectionArticlesList(username));
            checkingForOwnership(username, innerclass.indexList);
        }
    }

    private void gettingContentFromJsp(HttpServletRequest req, innerclass innerclass) {
        innerclass.setArticleName(req.getParameter("ArticleName"));
//        ArticleName = req.getParameter("ArticleName");
//        ArticleContent = req.getParameter("ArticleContent");
        innerclass.setArticleContent(req.getParameter("ArticleContent"));
//        articleCategory = req.getParameter("ArticleCategory");
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