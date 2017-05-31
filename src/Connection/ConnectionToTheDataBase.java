package Connection;

import java.sql.*;

/**
 * Created by ljam763 on 24/05/2017.
 */
public class ConnectionToTheDataBase {


    public enum ConnectionTypes {
        Admin, Get;
    }

    public Connection getConn() {
        return conn;
    }

    public static Connection  conn;

    public static Void ConnectionToBase(ConnectionTypes type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            switch (type) {
                case Admin:
                    conn =   DriverManager.getConnection("jdbc:mysql://mysql1.sporadic.co.nz:3306/uoaslashn", Config.getProperties(ConnectionTypes.Admin));
                    System.out.println("Connect to Admin");
                    break;
                case Get:
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

}
