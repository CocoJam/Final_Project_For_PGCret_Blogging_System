package ProfilePage;

import Login.LoginPassing;


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

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.cookieTracker;


/**
 * Created by ljam763 on 23/05/2017.
 */
public class Registration extends HttpServlet {
    private String username;
    private String password;
    private String dateofbirth;
    private java.sql.Date sqlFormateDate;
    private ProfilePAge profilePage;
    private ProfilePageDAO profilePageDAO;
    private int salt;
    private int iterations;
    private static final int KEY_LENGTH = 512;
    private static final Random RANDOM = new SecureRandom();

    //do Get to serve to Registration page jsp for update profile info
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            //The following if and else does two different actions, depends on the different GET calls, determined by the log parameter.
            //This is a call from the Profile page, "changeuserinfo" button. This is to redirect user to update the userInfo
            if (req.getParameter("log") != null) {
                if (req.getParameter("log").equals("Update Profile")) {
                    session.setAttribute("Upload", "ProfilePageUpload");
                    req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                    return;
                }
                //  TODO check logic of returning boolean from selectionUsersCheck()
                // This is related to the AJAX call in Registration.jsp
                else if (req.getParameter("log").equals("RegistrationCheck")) {
                    String usernameCheck = req.getParameter("usernameCheck");
                    System.out.println(usernameCheck);
                    LoginPassing loginPassing = new LoginPassing();
                    boolean asdna = loginPassing.selectionUsersCheck(usernameCheck);
                    System.out.println(asdna);
                    resp.getWriter().print(asdna);
                    return;
                }
            }
        System.out.println("Change user");
        cookieTracker(req, resp);
        return;

    }

    //The doPOST method receives information from the form within the registration page via the POST method.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            session.setAttribute("Upload", "ProfilePageUpload");

            // profileSetUp is used to simplify the logic here. All the setters of the profilepage Object is done within this method (see profileSetUp()).
            profileSetUp(req);

            profilePageDAO = new ProfilePageDAO();
            if (req.getParameter("log") != null) {
                System.out.println("Regs");
                //TODO refactor to switch statement if possible.
                // Scenario 1: The below is an editing scenario.
                if (req.getParameter("log").equals("Update Profile")) {
                    System.out.println("Trying for info update");
                    profilePage = profilePageDAO.updateUsersProfile((String) session.getAttribute("username"), password, profilePage, req.getParameter("password"));
                    System.out.println("info updated");
                    session.setAttribute("profileInfo", profilePage);
//                session.setAttribute("password",password);
                    closingConnection();
                    req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    return;
                }
//            Scenario 2: create new profile scenario
                else {
                    try {
                        System.out.println("Create");
                        session.setAttribute("password", req.getParameter("password"));
                        profilePageDAO.createUsersProfile(profilePage, req.getParameter("password"));
                        profilePage = profilePageDAO.getUsersProfile(profilePage.getUsername());
                        session.setAttribute("profileInfo", profilePage);
                        System.out.println(profilePage.getUsername());
                        session.setAttribute("username", profilePage.getUsername());
                        session.setAttribute("log", true);
                        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    } catch (SQLException e1) {
                        //If SQL Exception thrown by profilePageDAO.CreateUsersProfile() (when username already exists), then this is caught here and the user is redirected to the registration page where they have to start again.
                        closingConnection();
                        req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                        return;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            cookieTracker(req,resp);
        }

    }

    //This is the setup of the profile page whether for logged in or new registration users. Takes parameters from the form and sets them to the JAVABEAN Object instance variables.
    private void profileSetUp(HttpServletRequest req) {
        profilePage = new ProfilePAge();
        profilePage.setUsername(req.getParameter("username"));
        profilePage.setName(req.getParameter("name"));
        profilePage.setEducation(req.getParameter("education"));
        profilePage.setAddress(req.getParameter("address"));
        profilePage.setEthnicity(req.getParameter("ethnicity"));
        profilePage.setEmail(req.getParameter("email"));
        dateofbirth = req.getParameter("date");
        password = req.getParameter("password");
        profilePage.setIntroduction(req.getParameter("Introduction").replaceAll("<(/?script[^>]*)>", ""));
        profilePage.setProfilepic(req.getParameter("profilePicture"));
        System.out.println("profile pic :" + req.getParameter("profilePicture"));
        sqlDateparsing();
        profilePage.setDate(sqlFormateDate);
    }

    //Fixed the date parsing problem
    private void sqlDateparsing() {

        System.out.println(dateofbirth);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf1);
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
