package Comment;

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

//0. this class is the Servlet which receives the request from the client, communicates with the CommentsDAO with the selection, adding, or deleting request of the client and returning the appropriate responses back to the Client.

public class CommentsServlet extends HttpServlet{

    //0.a. instance variables used within the various CommentsServlets methods have been declared.

    private String username;
    private String comment;
    private int articleID;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO = new CommentsDAO();

    //0.b. note that there is no GET method as there is no need to create a specifically separate JSP page for comments.

    //1. This method 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        commentSetUp(req, session);
        commentsDAO.AddingCommentsToDataBase(articleID,username,comment);
        listOfComments = commentsDAO.selectionComments(articleID);
        checkingForOwner();
        session.setAttribute("commentlist",listOfComments);
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        return;
    }

    private void checkingForOwner() {
        for (Comments userComments : listOfComments) {
            if (userComments.getUsername().equals(username)){
                userComments.setOwner(true);
            }
            else{
                userComments.setOwner(false);
            }
        }
    }

    private void commentSetUp(HttpServletRequest req, HttpSession session) {
        username = (String) session.getAttribute("username");
        comment = req.getParameter("commentcontent");
        articleID = (int) session.getAttribute("articleID");
    }
}
