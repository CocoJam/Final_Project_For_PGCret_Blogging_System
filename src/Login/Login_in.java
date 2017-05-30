package Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static Connection.ConnectionToTheDataBase.closingConnection;


/**
 * Created by ljam763 on 23/05/2017.
 */
public class Login_in extends HttpServlet {
    private String username;
    private String password;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("Processing login");
        username = req.getParameter("username");
        password = req.getParameter("password");
        LoginPassing loginPassing = new LoginPassing();

        if (session.getAttribute("log") != null) {
            if ((boolean) session.getAttribute("log")) {
                //checking for when a user is logined in and other user tries to login within the same session.
                //This will logout the first person's account then login the second person. Hence when the second person logout everyone should be logined out.
                if (!username.equals(session.getAttribute("username")) && !username.equals(session.getAttribute("password"))) {
                    if (loginLogic(req, resp, session, loginPassing)) {
                        req.getRequestDispatcher("/ProfilePage").forward(req, resp);
                        return;
                    }
                } else {
                    System.out.println("Login");
                    req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    return;
                }
            }
        }
//        uncomment to check the hashing function
//        Login.Passwords_Checker passwords_checker = new Login.Passwords_Checker();
//        String hashedPassowrd = passwords_checker.hashing(password, 5 , 500);
//        loginPassing.selectionUsersNames(username,hashedPassowrd);
        loginLogic(req, resp, session, loginPassing);
        return;
    }

    // just refactor this out for convenience
    public boolean loginLogic(HttpServletRequest req, HttpServletResponse resp, HttpSession session, LoginPassing loginPassing) throws ServletException, IOException {
        if (loginPassing.selectionUsersNames(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("log", true);
            System.out.println("loged-in");
            System.out.println(session.getAttribute("username"));
            req.getRequestDispatcher("/ProfilePage").forward(req, resp);
            closingConnection();
            return true;
        } else {
            session.setAttribute("log", false);
            System.out.println("loged-in rejected");
            req.getRequestDispatcher("/login_page.jsp").forward(req, resp);
            return false;
        }
    }

    //Registration bug found when a user logged out and then click registration error appears.//fixed
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String registration = req.getParameter("Registration");
        session.setAttribute("Registration", false);
        if (registration != null) {
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
                        req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                        return;
                    }
                }
        }
    }
}
