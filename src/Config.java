import java.util.Properties;

/**
 * Created by ljam763 on 23/05/2017.
 */
public class Config {


        public static final String DB_NAME = "your_db_name";

        private static Properties properties;

        public static Properties getProperties() {
            if (properties == null) {
                properties = new Properties(System.getProperties());
                properties.setProperty("user", "ljam763");
                properties.setProperty("password", "b501347b");
                properties.setProperty("useSSL", "true");
            }
            return properties;
        }


}
