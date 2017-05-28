package ProfilePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Registration extends HttpServlet {
    private String username;
    private String password;
    private String dateofbirth;
    private java.sql.Date sqlFormateDate;
    private ProfilePAge profilePAge;
    private ProfilePageDAO profilePageDAO = new ProfilePageDAO();
    private int salt;
    private int iterations;
    private static final int KEY_LENGTH = 512;
    private static final Random RANDOM = new SecureRandom();

    //do Get to serve to Registration page jsp for update profile info
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("log", "Update");
        req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        profileSetUp(req);
//        updataTables.updataUsersNames(username,password);
        //        uncomment to check the hashing function
//        Login.Passwords_Checker passwords_checker = new Login.Passwords_Checker();
//        String hashedPassowrd = passwords_checker.hashing(password, 5 , 500);

        if (req.getParameter("log") != null) {
            System.out.println("Regs");
            if (req.getParameter("log").equals("ChangeUserInformation")) {
                System.out.println("Trying for info update");
                profilePAge = profilePageDAO.updataUsersNames((String) session.getAttribute("username"), (String) session.getAttribute("password"), profilePAge,password);
                System.out.println("info updated");
                session.setAttribute("profileInfo", profilePAge);
                req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                return;
            } else {
                try {
                    System.out.println("Create");
                    profilePageDAO.createUsersProfile(profilePAge, password);
                } catch (SQLException e1) {
                    req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                    return;
                }
            }
        }
        profilePAge = profilePageDAO.getUsersProfile(profilePAge.getUsername());
        session.setAttribute("profileInfo", profilePAge);
        System.out.println(profilePAge.getUsername());
        session.setAttribute("username", profilePAge.getUsername());
        session.setAttribute("password", password);
        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
        return;
    }

    private void profileSetUp(HttpServletRequest req) {
        profilePAge = new ProfilePAge();
        profilePAge.setUsername(req.getParameter("username"));
        profilePAge.setName(req.getParameter("name"));
        profilePAge.setEducation(req.getParameter("education"));
        profilePAge.setAddress(req.getParameter("address"));
        profilePAge.setEthnicity(req.getParameter("ethnicity"));
        profilePAge.setEmail(req.getParameter("email"));
        dateofbirth = req.getParameter("date");
        password = req.getParameter("password");
        sqlDateparsing();
        profilePAge.setDate(sqlFormateDate);
    }

    //Fixed the date parsing problem
    private void sqlDateparsing() {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (dateofbirth.equals("")) {
            sqlFormateDate = null;
            return;
        }
        try {
            date = sdf1.parse(dateofbirth);
        } catch (ParseException e) {
            System.out.println("not a date formate");
            e.printStackTrace();
        }
        sqlFormateDate = new java.sql.Date(date.getTime());
    }


}
