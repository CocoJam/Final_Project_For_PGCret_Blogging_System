package Connection;

import java.util.Properties;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Config {


    public static final String DB_NAME = "your_db_name";

    private static Properties properties;

    public static Properties getProperties(ConnectionToTheDataBase.ConnectionTypes type) {

        if (properties == null) {
            switch (type) {
                case Admin:

                    properties = new Properties(System.getProperties());
                    properties.setProperty("user", "ylin183");
                    properties.setProperty("password", "381nily");
                    properties.setProperty("useSSL", "true");
                    break;
                case Get:
                    properties = new Properties(System.getProperties());
                    properties.setProperty("user", "uoaslashnuser");
                    properties.setProperty("password", "uoaslashn");
                    properties.setProperty("useSSL", "true");
                    break;
            }
        }
        return properties;
    }


}
