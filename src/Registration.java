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
import java.util.Date;
import java.util.Random;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Registration extends HttpServlet {

    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private Date date;
    private ProfilePageDAO profilePageDAO = new ProfilePageDAO();
    private int salt;
    private int iterations;
    private static final int KEY_LENGTH = 512;
    private static final Random RANDOM = new SecureRandom();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UpdataTables updataTables = new UpdataTables();
        profileSetUp(req);
//        updataTables.updataUsersNames(username,password);
        //        uncomment to check the hashing function
//        Passwords_Checker passwords_checker = new Passwords_Checker();
//        String hashedPassowrd = passwords_checker.hashing(password, 5 , 500);
        updataTables.updataUsersProfile(username,password,name,email,address,education,ethnicity, (java.sql.Date) date);
        ProfilePAge profilePAge = profilePageDAO.getUsersProfile(username);
        session.setAttribute("profileInfo", profilePAge);
        session.setAttribute("username",username);
        session.setAttribute("password", password);
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req,resp);
        return;
    }

    private void profileSetUp(HttpServletRequest req) {
        username = req.getParameter("username");
        password = req.getParameter("password");
        name = req.getParameter("name");
        email =  req.getParameter("email");
        education = req.getParameter("education");
        address = req.getParameter("address");
        ethnicity = req.getParameter("ethnicity");
        date = (Date) req.getAttribute("date");
    }


}
