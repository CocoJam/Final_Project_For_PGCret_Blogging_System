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
    private Connection conn;
    private String username;
    private String password;
    private int salt;
    private int iterations;

    public boolean isLoginSucess() {
        return loginSucess;
    }

    public void setLoginSucess(boolean loginSucess) {
        this.loginSucess = loginSucess;
    }

    private boolean loginSucess = false;



    public void ConnectionDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/ljam763", Config.getProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean selectingInformation(String username, String password, HttpServletResponse resp) {

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT username, hashedpass FROM logins WHERE username = ? AND hashedpass = ?;"
            );
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                this.username = re.getString(1);
                this.password = re.getString(2);
                return true;
            }
        } catch (SQLException e1) {
            try {
                resp.sendRedirect("/login_page.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public int getSalt(String username, HttpServletRequest req, HttpServletResponse resp) {
        int salts = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT saltNum FROM logins WHERE username = ?;"
            );
            statement.setString(1, username);
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                salts = re.getInt(1);
            }
        } catch (SQLException e1) {
            try {
                req.getRequestDispatcher("/content_page.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return salts;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getAttribute("log"));
        if (session.getAttribute("log") != null){
            req.getRequestDispatcher("/content_page.jsp").forward(req, resp);
            return;
        }
        System.out.println(session.getId());
        Passwords_Checker pass = new Passwords_Checker();
        username = req.getParameter("username");
        password = req.getParameter("password");
        ConnectionDataBase();
        this.salt = getSalt(username,req,resp);
        byte[] slatArray = {(byte) salt};
        byte[] hasedPassword = pass.hash(password.toCharArray(), slatArray, 500);
        password = pass.base64Encode(hasedPassword);
        if (selectingInformation(username, password, resp)) {
            loginSucess = true;
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("log", true);
            req.getRequestDispatcher("/content_page.jsp").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/login_page.jsp").forward(req, resp);
            return;
        }
    }
}
