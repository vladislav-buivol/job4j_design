import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    static String propertyLocation = (System.getProperty("user.dir") + "\\chapter_002\\src\\main\\resources\\app.properties").replace("\\", File.separator);
    static Properties properties = new Properties();
    private static final String driver = "driver";
    private static final String url = "url";
    private static final String login = "login";
    private static final String password = "password";

    public static Properties readProperties() {
        try {
            InputStream stream = new FileInputStream(propertyLocation);
            properties.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String driver() {
        return String.valueOf(properties.get(driver));
    }

    public static String url() {
        return String.valueOf(properties.get(url));
    }

    public static String login() {
        return String.valueOf(properties.get(login));
    }

    public static String password() {
        return String.valueOf(properties.get(password));
    }
}
