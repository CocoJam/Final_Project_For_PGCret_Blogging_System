package Deleting;

import Article.Articles;
import Article.ArticlesIndexServlet;
import Login.LoginPassing;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static Connection.ConnectionToTheDataBase.*;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeletingServlet extends HttpServlet {
    private String username;
    private String password;
    private String sessionUsername;
    private String sessionpassword;
    private Articles article;
    private int articleId;
    private int commentId;
    private DeleteDAO deleteDAO;
    private LoginPassing loginPassing;

    //The dopost method is used to stop user to use non-conventional way to delete content or accounts.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req,resp);
        loginPassing =  new LoginPassing();
        deleteDAO = new DeleteDAO();
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session);
        if (req.getParameter("log") != null) {
            //Deleting this current Profile
            if (req.getParameter("log").equals("DeletingProfile")) {
                tryingTodeleteWholeProfile(req, resp, session);
                closingConnection();
                return;
                //Deleting this current Article
            } else if (req.getParameter("log").equals("DeleteArticle")) {
                tryingTodeleteWholeArticle(req, resp);
                closingConnection();
                return;
                //Deleting this comment.
            } else if (req.getParameter("log").equals("DeleteComment")) {
                System.out.println("converting id of article to int");
                System.out.println(req.getParameter("commentId"));
                try {
                    commentId = Integer.parseInt(req.getParameter("commentId"));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                tryingTodeleteAComment(req, resp, session);
                closingConnection();
                return;
                //Deleting the Media.
            } else if (req.getParameter("log").equals("DeleteMedia")){
                tryingToDeleteSpeificMedia(req, resp);
                //Deleting youtube.
            } else if (req.getParameter("log").equals("DeleteYoutube")){
//                TryingTodeleteAYoutubeVideo(req, resp);
            }
        }
        cookieTracker(req,resp);
        return;
    }


//    private void TryingTodeleteAYoutubeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String youtubeURL = req.getParameter("media");
//        System.out.println(youtubeURL);
//        deleteDAO.dropSpeificYoutube(youtubeURL);
//        closingConnection();
//        req.getRequestDispatcher("/Articles").forward(req, resp);
//    }

    private void tryingToDeleteSpeificMedia(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String filePath = servletContext.getRealPath(req.getParameter("media"));
        if (new File(filePath).exists()){
            System.out.println("Deleted? "+ new File(filePath).delete());
        }
        req.getRequestDispatcher("/ArticleUpload").forward(req, resp);
    }

    private void tryingTodeleteWholeArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session);
        article = (Articles) session.getAttribute("articleContents");
        if (article.getUsername().equals(sessionUsername)) {
            System.out.println("dropping this article");
            deleteDAO.dropSpeificArticle(article.getArticleid());
            closingConnection();
        }
        req.getRequestDispatcher("/ArticlesIndex").forward(req, resp);
        return;
    }

    private void tryingTodeleteWholeProfile(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        if (usernameAndPasswordCheckForDelete()) {
            deleteDAO.dropAllByUsername(username);
            closingConnection();
            req.getRequestDispatcher("/logout").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/ProfilePage").forward(req, resp);
            return;
        }
    }

    private void tryingTodeleteAComment(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        deleteDAO.dropSpeificComment(commentId);
        closingConnection();
        System.out.println("trying to delete the comment");
        req.getRequestDispatcher("/Articles").forward(req, resp);
        return;
    }

    private boolean usernameAndPasswordCheckForDelete() {
        return username.equals(sessionUsername) && password.equals(sessionpassword) && loginPassing.selectionUsersNames(username, password);
    }

    private void setupForUsernameAndPasswordCheck(HttpServletRequest req, HttpSession session) {
        username = req.getParameter("username");
        password = req.getParameter("password");
        sessionUsername = (String) session.getAttribute("username");
        sessionpassword = (String) session.getAttribute("password");
        return;
    }
}
