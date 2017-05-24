import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

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
        if (session.getAttribute("log") != null) {
            if ((boolean) session.getAttribute("log")) {
                System.out.println("Login");
                req.getRequestDispatcher("/WEB-INF/webthings/content_page.jsp").forward(req, resp);
                return;
            }
        }
        username = req.getParameter("username");
        password = req.getParameter("password");
        SelectionOfTables selectionOfTables = new SelectionOfTables();

//        uncomment to check the hashing function
//        Passwords_Checker passwords_checker = new Passwords_Checker();
//        String hashedPassowrd = passwords_checker.hashing(password, 5 , 500);
//        selectionOfTables.selectionUsersNames(username,hashedPassowrd);

        if (selectionOfTables.selectionUsersNames(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("log", true);
            System.out.println("loged-in");
            req.getRequestDispatcher("/WEB-INF/webthings/content_page.jsp").forward(req, resp);
            return;
        } else {
            session.setAttribute("log", false);
            System.out.println("loged-in rejected");
            req.getRequestDispatcher("/login_page.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String registration = req.getParameter("Registration");
        session.setAttribute("Registration", false);
        if (registration != null){
            if (registration.equals("Registration")){
                req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req,resp);
            }
        }
    }
}
