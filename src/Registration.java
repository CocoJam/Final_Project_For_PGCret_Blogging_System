import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Registration extends HttpServlet {
    private Connection conn;
    private String username;
    private String password;
    private int salt;
    private int iterations;
    private static final int KEY_LENGTH = 512;
    private static final Random RANDOM = new SecureRandom();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UpdataTables updataTables = new UpdataTables();

        username = req.getParameter("username");
        password = req.getParameter("password");

        //        uncomment to check the hashing function
//        Passwords_Checker passwords_checker = new Passwords_Checker();
//        String hashedPassowrd = passwords_checker.hashing(password, 5 , 500);

        updataTables.updataUsersNames(username,password);
        session.setAttribute("username",username);
        session.setAttribute("password", password);
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }


}
