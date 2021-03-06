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
    private String username;
    private String comment;
    private int commentId;
    private int articleID;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO;

    //Article servlet will reroute here.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req, resp);
        commentsDAO = new CommentsDAO();
        Comments comments = null;
        HttpSession session = req.getSession();
        commentSetUp(req, session);
        String commentStatus = req.getParameter("comments");
        if (commentStatus != null) {
            //Scenario 1: This is to add a new comment
            if (commentStatus.equals("Add a Comment")) {
                commentsDAO.AddingCommentsToDataBase(articleID, username, comment);
                comments = commentsDAO.selectionLastComment();
            }
            //Scenario 2: Editing comments
            else if (commentStatus.equals("EditComment")) {
                try {
                    commentId = Integer.parseInt(req.getParameter("commentId"));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                //updating comments (using DAO)
                commentsDAO.editComments(comment, commentId);
                comments = commentsDAO.selectionComment(commentId);
            }
            if (comments != null) {
                JSONObject jsonObject = getJsonObject(comments);
                System.out.println(jsonObject);
                resp.getWriter().print(jsonObject);
                return;
            }
            cookieTracker(req, resp);
        }
        //Grabbing list again since it is fully updated.
        System.out.println("selected article comments   ");
        listOfComments = commentsDAO.selectionComments(articleID);
        if (listOfComments != null) {
            checkingForOwner();
            session.setAttribute("commentlist", listOfComments);
        }
        System.out.println("dispatcher");
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
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
    private void checkingForOwner() {
        for (Comments userComments : listOfComments) {
            if (userComments.getUsername().equals(username)) {
                System.out.println("yes");
                userComments.setOwner(true);
            } else {
                System.out.println("no");
                userComments.setOwner(false);
            }
        }
    }

    //Setup for the comments based on the attribute of the session and the given content.
    private void commentSetUp(HttpServletRequest req, HttpSession session) {
        username = (String) session.getAttribute("username");
        comment = req.getParameter("commentcontent");
        articleID = (int) session.getAttribute("articleID");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
