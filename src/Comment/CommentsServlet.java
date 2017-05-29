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
public class CommentsServlet extends HttpServlet {
    private String username;
    private String comment;
    private int commentId;
    private int articleID;
    private List<Comments> listOfComments;
    private CommentsDAO commentsDAO = new CommentsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        commentSetUp(req, session);
        String commentStat = req.getParameter("comments");
        if (commentStat != null) {
            if (commentStat.equals("Add a Comment")) {
                commentsDAO.AddingCommentsToDataBase(articleID, username, comment);
            } else if (commentStat.equals("EditComment")) {
                try {
                    commentId = Integer.parseInt(req.getParameter("commentId"));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                commentsDAO.editComments(comment, commentId);
            }
        }
        listOfComments = commentsDAO.selectionComments(articleID);
        checkingForOwner();
        session.setAttribute("commentlist", listOfComments);
        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
        return;

    }

    private void checkingForOwner() {
        for (Comments userComments : listOfComments) {
            if (userComments.getUsername().equals(username)) {
                userComments.setOwner(true);
            } else {
                userComments.setOwner(false);
            }
        }
    }

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
