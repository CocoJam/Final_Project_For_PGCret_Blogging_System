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
//
//    private String username;
//    private String password;
//    private String sessionUsername;
//    private String sessionpassword;
//    private Articles article;
//    private int commentId;
//    private DeleteDAO deleteDAO;
//    private LoginPassing loginPassing;

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
                    System.out.println("deleting article");
                    tryingTodeleteWholeArticle(req, resp, innerclass);
                    closingConnection();
                    return;
                    //Deleting this comment.
                } else if (req.getParameter("log").equals("DeleteComment")) {
                    System.out.println("converting id of article to int");
                    System.out.println(req.getParameter("commentId"));
                    int commentId = 0;
                    try {
                        commentId = Integer.parseInt(req.getParameter("commentId"));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    tryingTodeleteAComment(innerclass, commentId);
                    closingConnection();
                    return;
                    //Deleting the Media.
                } else if (req.getParameter("log").equals("DeleteMedia")) {
                    tryingToDeleteSpeificMedia(req, resp);
                    //Deleting youtube.
                }
//            else if (req.getParameter("log").equals("DeleteYoutube")){
////                TryingTodeleteAYoutubeVideo(req, resp);
//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            cookieTracker(req, resp);
        }
        cookieTracker(req, resp);
        return;
    }


//    private void TryingTodeleteAYoutubeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String youtubeURL = req.getParameter("media");
//        System.out.println(youtubeURL);
//        deleteDAO.dropSpeificYoutube(youtubeURL);
//        closingConnection();
//        req.getRequestDispatcher("/Articles").forward(req, resp);
//    }

    //Used to delete a specific media based on the file path of it.
    private void tryingToDeleteSpeificMedia(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String filePath = servletContext.getRealPath(req.getParameter("media"));
        if (new File(filePath).exists()) {
            System.out.println("Deleted? " + new File(filePath).delete());
        }
        req.getRequestDispatcher("/ArticleUpload").forward(req, resp);
    }

    //deleting the whole article
    private void tryingTodeleteWholeArticle(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) throws ServletException, IOException {
        HttpSession session = req.getSession();
        setupForUsernameAndPasswordCheck(req, session, innerclass);
        Articles article = (Articles) session.getAttribute("articleContents");
        if (article.getUsername().equals(innerclass.sessionUsername)) {
            System.out.println("dropping this article");
            innerclass.deleteDAO.dropSpecificArticlesAll(article.getArticleid());
        }
        cookieTracker(req, resp);
        return;
    }

    //Deleting the whole profile.
    private void tryingTodeleteWholeProfile(HttpServletRequest req, HttpServletResponse resp, innerclass innerclass) throws ServletException, IOException {
        if (usernameAndPasswordCheckForDelete(innerclass)) {
            innerclass.deleteDAO.dropAllByUsername(innerclass.username);
            req.getRequestDispatcher("/logout").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/ProfilePage").forward(req, resp);
            return;
        }
    }

    //Deleting a speific comment given by the comment id.
    private void tryingTodeleteAComment(innerclass innerclass, int commentId) throws ServletException, IOException {
        innerclass.deleteDAO.dropSpeificComment(commentId);
        closingConnection();
        System.out.println("trying to delete the comment");
//        req.getRequestDispatcher("/Articles").forward(req, resp);
        return;
    }

    // Password and username check of deleting profile.
    private boolean usernameAndPasswordCheckForDelete(innerclass innerclass) {
        return innerclass.username.equals(innerclass.sessionUsername) && innerclass.password.equals(innerclass.sessionpassword) && innerclass.loginPassing.selectionUsersNames(innerclass.username, innerclass.password);
    }

    // setup for overall delete function when user is deleting their profile.
    private void setupForUsernameAndPasswordCheck(HttpServletRequest req, HttpSession session, innerclass innerclass) {
        innerclass.setUsername(req.getParameter("username"));
//        username = req.getParameter("username");
        innerclass.setPassword(req.getParameter("password"));
//        password = req.getParameter("password");
//        sessionUsername = (String) session.getAttribute("username");
        innerclass.setSessionUsername((String) session.getAttribute("username"));
//        sessionpassword = (String) session.getAttribute("password");
        innerclass.setSessionpassword((String) session.getAttribute("password"));
        return;
    }
}
