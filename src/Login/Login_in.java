package Login;

import Article.ArticleListObjectDAO;
import Article.Articles;
import Friend.Friend;
import Friend.FriendDAO;
import com.sun.xml.internal.bind.v2.TODO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.conn;
import static Connection.ConnectionToTheDataBase.cookieTracker;


/**
 * Created by ljam763 on 23/05/2017.
 */
public class Login_in extends HttpServlet {
    private String username;
    private String password;
    private Passwords_Checker passwords_checker = new Passwords_Checker();
//    private List<Articles> indexList = new ArrayList<>();

    //doPost checks in login is possible and if so redirects to Profile page
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("Processing login");
        username = req.getParameter("username");
        password = req.getParameter("password");
        LoginPassing loginPassing = new LoginPassing(); //See loginPassing class: stores all the methods for login including DAO query the database.

        if (session.getAttribute("log") != null) {
            if ((boolean) session.getAttribute("log")) {
                //checking for when a user is logined in and other user tries to login within the same session.
                //This will logout the first person's account then login the second person. Hence when the second person logout everyone should be logged out.

                //this is to bounce people out if people are already logged in. IF username and password is not in session bounces to profile and logs out the previous user.
                if (!username.equals(session.getAttribute("username")) && !password.equals(session.getAttribute("password"))) {
                    if (loginLogic(req, resp, session, loginPassing)) {
                        closingConnection();
                        req.getRequestDispatcher("/ProfilePage").forward(req, resp);
                        return;
                    }

                    //There is a major bug, even though there is someone logged in ppl can still bypass it because it does not check the login here.
                } else {
                    System.out.println("Login");
                    closingConnection();
                    req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    return;
                }
            }
        }
        //TODO check hashing algorithm, need to check if it works.

//        uncomment to check the hashing function
//        Login.Passwords_Checker passwords_checker = new Login.Passwords_Checker();
//        String hashedPassword = passwords_checker.hashing(password, 5 , 500);
//        loginPassing.selectionUsersNames(username,hashedPassword);
        if (!password.trim().equals("") && !password.trim().isEmpty() && password.trim() != null) {
            System.out.println("hashing");
            loginLogic(req, resp, session, loginPassing);
        } else {
            session.setAttribute("log", false); //TODO refactoring for login status.
            System.out.println("logged-in rejected");
            req.getRequestDispatcher("/login_page.jsp").forward(req, resp);
        }
        return;

        //TODO dispatch back to the login page.
    }


    //This is to check the login logic used by doPost to check user login from the login_page.jsp.
    // TODO just refactor this out for convenience
    public boolean loginLogic(HttpServletRequest req, HttpServletResponse resp, HttpSession session, LoginPassing loginPassing) throws ServletException, IOException {
        if (loginPassing.selectionUsersNames(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("articleList", "self");
            session.setAttribute("log", true); //TODO refactoring for login status.

//            List<Articles> indexList = new ArticleListObjectDAO().selectionArticlesList(username);
//            session.setAttribute("IndexOfInterest", indexList);
//            System.out.println(indexList);

            List<Friend> friendList = new FriendDAO().selectionListOfFriends(username);
            List<String> userList = new FriendDAO().GetAllPeopleUsername();
            session.setAttribute("firendlist", friendList);
            session.setAttribute("userlist",userList);
            System.out.println("logged-in");
            System.out.println(session.getAttribute("username"));
            req.getRequestDispatcher("/ProfilePage").forward(req, resp); //TODO to take out.
            return true;
        } else {
            session.setAttribute("log", false); //TODO refactoring for login status.
            System.out.println("logged-in rejected");
            req.getRequestDispatcher("/login_page.jsp").forward(req, resp);
            return false;
        }
    }


    //doGet is when a GET request sent from the login page where the user wants to goto the register page to register a new account.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String registration = req.getParameter("Registration");
        session.setAttribute("Registration", false);
        if (registration != null) { //TODO refactor to switch statement.
            //checking if user is login already bound back to profilepage when registration is clicked.
            if (session.getAttribute("log") == null) {
                req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                return;
            }
            if ((boolean) session.getAttribute("log")) {
                req.getRequestDispatcher("/ProfilePage").forward(req, resp);
                return;
            } else {
                if (registration.equals("Registration")) {
                    session.setAttribute("Upload", "ArticlesUpload");
                    req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                    return;
                }
            }
        }
        else {
            cookieTracker(req,resp);
        }
    }
}
