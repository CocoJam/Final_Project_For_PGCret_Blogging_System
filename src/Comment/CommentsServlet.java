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
public class CommentsServlet extends HttpServlet{

    private CommentsDAO commentsDAO = new CommentsDAO();

    String sessionUser = "";
    List<Comments> commentList = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        sessionUser = (String)req.getSession().getAttribute("username");
        req.setAttribute("Commenter", sessionUser);
        req.getRequestDispatcher("/WEB-INF/webthings/Comments.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String commenterUsername = (String) session.getAttribute("username");
        String commentContent = req.getParameter("commentText");

        //Making a new comment

//        if (req.getParameter("commentSubmit").equals("submitComment")){
            commentsDAO.lodgeComment(36, commenterUsername, commentContent);
//        }

        System.out.println(session.getAttribute("articleID"));
//        if (session.getAttribute("articleID").equals(22)){
            commentList = commentsDAO.selectionComments(22);
            req.setAttribute("commentList", commentList);
//        }
        req.getRequestDispatcher("/WEB-INF/webthings/Comments.jsp").forward(req,resp);
        return;
    }
}
