import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class ConnectionToTheDataBase {

    private Connection conn;

    public void ConnectionToBase(String type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            if (type.equals("admin")){
            this.conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties("admin"));
                System.out.println("admin confirmed");
            }
            else if (type.equals("get")){
                this.conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashnuser", Config.getProperties("get"));
                System.out.println("get confirmed");
            }

            System.out.println("Connection success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectionToTheDataBase connectionToTheDataBase = new ConnectionToTheDataBase();
        connectionToTheDataBase.ConnectionToBase("get");

    }

}
