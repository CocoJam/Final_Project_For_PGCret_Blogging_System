import java.util.Properties;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Config {


        public static final String DB_NAME = "your_db_name";

        private static Properties properties;

        public static Properties getProperties(String type) {
            if (type.equals("admin")){

            if (properties == null) {
                properties = new Properties(System.getProperties());
                properties.setProperty("user", "ljam763");
                properties.setProperty("password", "b501347b");
                properties.setProperty("useSSL", "true");
            }}
            else if (type.equals("get")){
                properties = new Properties(System.getProperties());
                properties.setProperty("user", "uoaslashnuser");
                properties.setProperty("password", "uoaslashn");
                properties.setProperty("useSSL", "true");
            }
            return properties;
        }


}
