package Friend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Connection.ConnectionToTheDataBase.cookieLogOut;
import static Connection.ConnectionToTheDataBase.cookieTracker;

/**
 * Created by ljam763 on 8/06/2017.
 */
public class FriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req,resp);
        try{
        HttpSession session = req.getSession();
        FriendDAO friendDAO = new FriendDAO();
        System.out.println("posted");
        String username = req.getParameter("username");
        String friendname = req.getParameter("friendname");
        String friendprocess = req.getParameter("friendprocess");
        //Two way adding friends.
        if (username != null && friendname != null && friendprocess != null && friendprocess.equals("add")){
           boolean addedoneway = friendDAO.AddFriends(username,friendname);
           boolean addedtwoway = friendDAO.AddFriends(friendname,username);
        }
        //Two way deleting friends
        else if (username != null && friendname != null && friendprocess != null && friendprocess.equals("unadd")){
            boolean deleteFriends = friendDAO.DeleteFriends(username,friendname);
            boolean deleteFriends1 = friendDAO.DeleteFriends(friendname,username);
        }
        else {
            cookieTracker(req,resp);
            return;
        }
        System.out.println("ajax came here");
        List<Friend> friendList =friendDAO.selectionListOfFriends(username);
        session.setAttribute("firendlist", friendList);
        System.out.println(friendList);
        req.getRequestDispatcher("/ProfilePage").forward(req, resp);}
        catch (Exception e){
            cookieTracker(req,resp);
        }
        return;
    }
}
