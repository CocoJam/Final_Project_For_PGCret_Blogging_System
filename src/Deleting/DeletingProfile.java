package Deleting;

import Article.Articles;
import Login.LoginPassing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeletingProfile extends HttpServlet {
    private String username;
    private String password;
    private String sessionUsername;
    private String sessionpassword;
    private Articles article;
    private DeleteDAO deleteDAO = new DeleteDAO();
    private LoginPassing loginPassing = new LoginPassing();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session);
        if (req.getParameter("log") != null) {
            if (req.getParameter("log").equals("DeletingProfile")) {
                tryingTodeleteWholeProfile(req, resp, session);
                return;
            } else if (req.getParameter("log").equals("DeleteArticle")) {
                tryingTodeleteWholeArticle(req, resp);
                return;
            }
        }
    }

    private void tryingTodeleteWholeArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req,session);
        article = (Articles) session.getAttribute("articleContents");
        System.out.println(article.getUsername());
        System.out.println(article.getArticleid()+ "This is the id");
        System.out.println(username);
        if (article.getUsername().equals(sessionUsername)) {
            System.out.println("dropping this article");
            deleteDAO.dropSpeificArticle(article.getArticleid());
        }
        req.getRequestDispatcher("/ArticlesIndex").forward(req, resp);
        return;
    }

    private void tryingTodeleteWholeProfile(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        if (username.equals(sessionUsername) && password.equals(sessionpassword) && loginPassing.selectionUsersNames(username, password)) {
            deleteDAO.dropAllByUsername(username);
            req.getRequestDispatcher("/logout").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/ProfilePage").forward(req, resp);
            return;
        }
    }

    private void setupForUsernameAndPasswordCheck(HttpServletRequest req, HttpSession session) {
        username = req.getParameter("username");
        password = req.getParameter("password");
        sessionUsername = (String) session.getAttribute("username");
        sessionpassword = (String) session.getAttribute("password");
        return;
    }
}
