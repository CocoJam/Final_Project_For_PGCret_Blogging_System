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

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.conn;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginPassing =  new LoginPassing();
        deleteDAO = new DeleteDAO();
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session);
        System.out.println(username);
        System.out.println(password);
        System.out.println(sessionUsername);
        System.out.println(sessionpassword);
        if (req.getParameter("log") != null) {
            if (req.getParameter("log").equals("DeletingProfile")) {
                tryingTodeleteWholeProfile(req, resp, session);
                closingConnection();
                return;
            } else if (req.getParameter("log").equals("DeleteArticle")) {
                tryingTodeleteWholeArticle(req, resp);
                closingConnection();
                return;
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
            } else if (req.getParameter("log").equals("DeleteMedia")){
                tryingToDeleteSpeificMedia(req, resp);
            } else if (req.getParameter("log").equals("DeleteYoutube")){
                TryingTodeleteAYoutubeVideo(req, resp);
            }
        }
    }

    private void TryingTodeleteAYoutubeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String youtubeURL = req.getParameter("media");
        System.out.println(youtubeURL);
        deleteDAO.dropSpeificYoutube(youtubeURL);
        closingConnection();
        req.getRequestDispatcher("/Articles").forward(req, resp);
    }

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
