package Article;

import Comment.Comments;
import Comment.CommentsDAO;

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
public class ArticleServlet extends HttpServlet {
    private ArticlesDAO articlesDAO = new ArticlesDAO();
    private int ArticleID;
    private String ArticleName;
    private String ArticleContent;
    private Articles article;
    private HttpSession session;
    private List<Articles> indexList;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO = new CommentsDAO();

    public List<Comments> gettingTheListOfComments(int articleID){
        return listOfComments = commentsDAO.selectionComments(articleID);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editing = req.getParameter("edit");
        session = req.getSession();

        try{ArticleID = Integer.parseInt(req.getParameter("acticleId"));}
        catch (NumberFormatException e){
            System.out.println(e);
        }
        System.out.println(ArticleID + "articleid");
        article = articlesDAO.selectionArticles(ArticleID);
        if (article.getUsername().equals(session.getAttribute("username"))){
            article.setOwner(true);
        }
        session.setAttribute("articleID", ArticleID);
        session.setAttribute("articleContents", article);
        listOfComments = gettingTheListOfComments(ArticleID);
        session.setAttribute("commentlist",listOfComments);
//        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        req.getRequestDispatcher("/Comments").forward(req, resp);
        return;
    }

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
            } else if (addingArticles.equals("EditArticle")) {
                session.setAttribute("articleID", ArticleID);
                session.setAttribute("Upload", "ArticlesUpload");
                req.setAttribute("articleContents", article);
                req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
                return;
            } else if (addingArticles.equals("Editted")) {
                ArticleName = req.getParameter("ArticleName");
                ArticleContent = req.getParameter("ArticleContent");
                article = articlesDAO.updataArticles(ArticleName, ArticleContent, ArticleID);
                req.setAttribute("articleContents", article);
                session.setAttribute("Upload", null);
                req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                return;
            } else if (addingArticles.equals("addingToDataBase")) {
                System.out.println(username);
                String ArticleName = req.getParameter("ArticleName");
                String ArticleContent = req.getParameter("ArticleContent");
                articlesDAO.madeArticles(ArticleName, username, ArticleContent);
                String Listformation =(String)session.getAttribute("articleList");
                System.out.println((String)session.getAttribute("articleList"));
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
