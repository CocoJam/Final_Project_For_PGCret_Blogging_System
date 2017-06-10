package Connection;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class ConnectionToTheDataBase {


    public enum ConnectionTypes {
        Admin, Get;
    }

    public Connection getConn() {
        ConnectionToBase("GET");
        return conn;
    }

    public static Connection  conn;

    public Void ConnectionToBase(String type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            switch (type) {
                case "ADMIN":
                    conn =   DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties(ConnectionTypes.Admin));
                    System.out.println("Connect to Admin");
                    break;
                case "GET":
                    conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties(ConnectionTypes.Get));
                    System.out.println("Connect to Get");
                    break;
            }
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    public static void closingConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Connection is closed :"+conn.isClosed());
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cookieTracker(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pagemark")){
                try {
                    req.getRequestDispatcher(cookie.getValue()).forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

}
