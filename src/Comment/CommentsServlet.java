package Comment;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.cookieLogOut;
import static Connection.ConnectionToTheDataBase.cookieTracker;


/**
 * Created by ljam763 on 25/05/2017.
 */

//Servlet is just to renew just after you add a comment OR allow us to populate how and where we want to add a comment.
public class CommentsServlet extends HttpServlet {

    public class innerclass {
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public int getArticleID() {
            return articleID;
        }

        public void setArticleID(int articleID) {
            this.articleID = articleID;
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

        private String username;
        private String comment;
        private int commentId;
        private int articleID;
        private List<Comments> listOfComments;
        private CommentsDAO commentsDAO;

        public Comments getComments() {
            return comments;
        }

        public void setComments(Comments comments) {
            this.comments = comments;
        }

        private Comments comments;
    }

//    private String username;
//    private String comment;
//    private int commentId;
//    private int articleID;
//    private List<Comments> listOfComments;
//    private CommentsDAO commentsDAO;

    //Article servlet will reroute here.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        try {
            CommentsDAO commentsDAO = new CommentsDAO();
//        Comments comments = null;
            HttpSession session = req.getSession();
            innerclass innerclass = new innerclass();
            innerclass.setCommentsDAO(commentsDAO);
            commentSetUp(req, session, innerclass);
            String commentStatus = req.getParameter("comments");
            if (commentStatus != null) {
                //Scenario 1: This is to add a new comment
                if (commentStatus.equals("Add a Comment")) {
//                commentsDAO.AddingCommentsToDataBase(articleID, username, comment);
                    innerclass.commentsDAO.AddingCommentsToDataBase(innerclass.articleID, innerclass.username, innerclass.comment);
                    innerclass.setComments(innerclass.commentsDAO.selectionLastComment(innerclass.articleID, innerclass.username, innerclass.comment));
//                   comments = commentsDAO.selectionLastComment();
                }
                //Scenario 2: Editing comments
                else if (commentStatus.equals("EditComment")) {
                    int commentId = 0;
                    try {
                        commentId = Integer.parseInt(req.getParameter("commentId"));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    //updating comments (using DAO)
                    innerclass.commentsDAO.editComments(innerclass.comment, commentId);
                    innerclass.setComments(innerclass.commentsDAO.selectionComment(commentId));
//                comments = commentsDAO.selectionComment(commentId);
                    return;
                }
                System.out.println(innerclass.comments);
                if (innerclass.comments != null) {
                    JSONObject jsonObject = getJsonObject(innerclass.comments);
                    System.out.println(jsonObject);
                    resp.getWriter().print(jsonObject);
                    return;
                }
                cookieTracker(req, resp);
            }
            //Grabbing list again since it is fully updated.
            System.out.println("selected article comments   ");
            innerclass.setListOfComments(innerclass.commentsDAO.selectionComments(innerclass.articleID));
//        listOfComments = commentsDAO.selectionComments(articleID);
            if (innerclass.listOfComments != null) {
                checkingForOwner(innerclass.listOfComments, innerclass);
                session.setAttribute("commentlist", innerclass.listOfComments);
            }
            System.out.println("dispatcher");
            req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        }
        catch (Exception e){
            e.printStackTrace();
            cookieTracker(req,resp);
        }
        return;
    }

    //JsonObject setup for writing ajax comments
    private JSONObject getJsonObject(Comments comments) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CommentId", comments.getCommentId());
        jsonObject.put("ActicleId", comments.getActicleId() + "");
        jsonObject.put("Username", comments.getUsername() + "");
        jsonObject.put("CommentedTime", comments.getCommentedTime() + "");
        jsonObject.put("Content", comments.getContent() + "");
        return jsonObject;
    }

    //Checking owner of the particular comment based on the session username.
    private void checkingForOwner(List<Comments> listOfComments, innerclass innerclass) {
        for (Comments userComments : listOfComments) {
            if (userComments.getUsername().equals(innerclass.username)) {
                System.out.println("yes");
                userComments.setOwner(true);
            } else {
                System.out.println("no");
                userComments.setOwner(false);
            }
        }
    }

    //Setup for the comments based on the attribute of the session and the given content.
    private void commentSetUp(HttpServletRequest req, HttpSession session, innerclass innerclass) {
        innerclass.setUsername((String) session.getAttribute("username"));
//        username = (String) session.getAttribute("username");
        innerclass.setComment(req.getParameter("commentcontent"));
//        comment = req.getParameter("commentcontent");
        System.out.println(session.getAttribute("articleID"));
        innerclass.setArticleID((int) session.getAttribute("articleID"));
//        articleID = (int) session.getAttribute("articleID");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
