package Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 11/06/2017.
 */
public class ArticleCartServlet extends HttpServlet{
    //This doPost just set the session attribute to the cartlist, which is allowing the user to store temp articles.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        if ((List<String>) session.getAttribute("cartlist") == null) {
            When_CartList_Is_Null(session);
        }
        if (req.getParameter("cartunadd") != null) {
            cartunadd(req, session);
        }
        if (req.getParameter("cartadd") != null) {
            cartadd(req, session);
        }
    }
    //This method is ran when the user added the card of article within the cart box and this allow attaching more cards on to the arrayList. By ajax
    private void cartadd(HttpServletRequest req, HttpSession session) {
        if ((List<String>) session.getAttribute("cartlist") != null) {
            ((List<String>) session.getAttribute("cartlist")).add(req.getParameter("cartadd"));
        }
    }
    //This method is ran when the user remove the card of article within the cart box and this allow remove the cards on to the arrayList. By ajax
    private void cartunadd(HttpServletRequest req, HttpSession session) {
        if ((List<String>) session.getAttribute("cartlist") != null) {
            ((List<String>) session.getAttribute("cartlist")).remove(req.getParameter("cartunadd"));
        }
    }
    //This method is ran when the user added the card of article within the cart box and this allow remove the cards on to the arrayList. By ajax
    private void When_CartList_Is_Null(HttpSession session) {
        List<String> cartlist = new ArrayList<>();
        session.setAttribute("cartlist", cartlist);
    }
}
