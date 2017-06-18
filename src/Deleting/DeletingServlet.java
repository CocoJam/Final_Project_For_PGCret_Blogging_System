package Deleting;

import Article.Articles;
import Login.LoginPassing;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static Connection.ConnectionToTheDataBase.*;

/**
 * Created by ljam763 on 28/05/2017.
 */
public class DeletingServlet extends HttpServlet {

    public class innerclass {
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSessionUsername() {
            return sessionUsername;
        }

        public void setSessionUsername(String sessionUsername) {
            this.sessionUsername = sessionUsername;
        }

        public String getSessionpassword() {
            return sessionpassword;
        }

        public void setSessionpassword(String sessionpassword) {
            this.sessionpassword = sessionpassword;
        }

        public Articles getArticle() {
            return article;
        }

        public void setArticle(Articles article) {
            this.article = article;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public DeleteDAO getDeleteDAO() {
            return deleteDAO;
        }

        public void setDeleteDAO(DeleteDAO deleteDAO) {
            this.deleteDAO = deleteDAO;
        }

        public LoginPassing getLoginPassing() {
            return loginPassing;
        }

        public void setLoginPassing(LoginPassing loginPassing) {
            this.loginPassing = loginPassing;
        }

        private String username;
        private String password;
        private String sessionUsername;
        private String sessionpassword;
        private Articles article;
        private int commentId;
        private DeleteDAO deleteDAO;
        private LoginPassing loginPassing;
    }

    //The dopost method is used to stop user to use non-conventional way to delete content or accounts.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        try {
            HttpSession session = req.getSession();
            innerclass innerclass = new innerclass();
            innerclass.setDeleteDAO(new DeleteDAO());
            innerclass.setLoginPassing(new LoginPassing());
            setupForUsernameAndPasswordCheck(req, session, innerclass);

            if (req.getParameter("log") != null) {
                //Deleting this current Profile
                if (req.getParameter("log").equals("Deleting Profile")) {
                    tryingTodeleteWholeProfile(req, resp, innerclass);
                    closingConnection();
                    return;
                    //Deleting this current Article
                } else if (req.getParameter("log").equals("DeleteArticle")) {
                    
                    tryingTodeleteWholeArticle(req, resp, innerclass);
                    closingConnection();
                    return;
                    //Deleting this comment.
                } else if (req.getParameter("log").equals("DeleteComment")) {
                    
                    
                    int commentId = 0;
                    try {
                        commentId = Integer.parseInt(req.getParameter("commentId"));
                    } catch (NumberFormatException e) {
                        
                    }
                    tryingTodeleteAComment(innerclass, commentId);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
        cookieTracker(req, resp);
        return;
    }

    
    //Used to delete a specific media based on the file path of it.
//    private void tryingToDeleteSpeificMedia(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        String filePath = servletContext.getRealPath(req.getParameter("media"));
//        if (new File(filePath).exists()) {
//        }
//        req.getRequestDispatcher("ArticleUpload").forward(req, resp);
//    }

    //deleting the whole article
    private void tryingTodeleteWholeArticle(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) throws ServletException, IOException {
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session, innerclass);
        Articles article = (Articles) session.getAttribute("articleContents");
        if (article.getUsername().equals(innerclass.sessionUsername)) {
            innerclass.deleteDAO.dropSpecificArticlesAll(article.getArticleid());
            req.getRequestDispatcher("ProfilePage").forward(req, resp);
            return;
        }
        cookieTracker(req, resp);
        return;
    }

    //Deleting the whole profile.
    private void tryingTodeleteWholeProfile(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) throws ServletException, IOException {
        if (usernameAndPasswordCheckForDelete(innerclass)) {
            innerclass.deleteDAO.dropAllByUsername(innerclass.username);
            req.getRequestDispatcher("logout").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("ProfilePage").forward(req, resp);
            return;
        }
    }

    //Deleting a speific comment given by the comment id.
    private void tryingTodeleteAComment(innerclass innerclass, int commentId) throws ServletException, IOException {
        innerclass.deleteDAO.dropSpeificComment(commentId);
        return;
    }

    // Password and username check of deleting profile.
    private boolean usernameAndPasswordCheckForDelete(innerclass innerclass) {
        return innerclass.username.equals(innerclass.sessionUsername) && innerclass.password.equals(innerclass.sessionpassword) && innerclass.loginPassing.selectionUsersNames(innerclass.username, innerclass.password);
    }

    // setup for overall delete function when user is deleting their profile.
    private void setupForUsernameAndPasswordCheck(HttpServletRequest req, HttpSession session, innerclass innerclass) {
        innerclass.setUsername(req.getParameter("username"));
        innerclass.setPassword(req.getParameter("password"));
        innerclass.setSessionUsername((String) session.getAttribute("username"));
        innerclass.setSessionpassword((String) session.getAttribute("password"));
        return;
    }
}
