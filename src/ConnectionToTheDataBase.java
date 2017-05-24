import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class ConnectionToTheDataBase {
public enum ConnectionTypes{
    Admin,Get;
}
    private Connection conn;

    public void ConnectionToBase(ConnectionTypes type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
// The admin one
            switch (type){
                case Admin:
            this.conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties(ConnectionTypes.Admin));
                System.out.println("admin confirmed");
            break;
                case Get:
                this.conn = DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties(ConnectionTypes.Get));
                System.out.println("get confirmed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectionToTheDataBase connectionToTheDataBase = new ConnectionToTheDataBase();

    }

}
