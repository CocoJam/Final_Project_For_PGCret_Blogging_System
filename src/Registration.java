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


    public Connection ConnectionDataBase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           return conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/ljam763", Config.getProperties(ConnectionToTheDataBase.ConnectionTypes.Admin));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updataDataBase(String username, String password, int salt, int iterations){
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO logins VALUES( ?, ?, ?, ? );"
            );{
                statement.setString(1, username);
                statement.setString(2,password);
                statement.setInt(3, salt);
                statement.setInt(4, iterations);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        System.out.println(session.getId());
        Passwords_Checker pass = new Passwords_Checker();
        username = req.getParameter("username");
        String password123 = req.getParameter("password");
        salt = Integer.parseInt(req.getParameter("Lucky number"));
        iterations = 500;
        byte[] saltbyte = {(byte) salt};
        byte[] passwordHashed = pass.hash(password123.toCharArray(), saltbyte, iterations);
        password = pass.base64Encode(passwordHashed);
        ConnectionDataBase();
        updataDataBase(username, password, salt, iterations);
        session.setAttribute("username",username);
        session.setAttribute("password", password123);
        req.getRequestDispatcher("/content_page.jsp").forward(req,resp);
        return;
    }


}
