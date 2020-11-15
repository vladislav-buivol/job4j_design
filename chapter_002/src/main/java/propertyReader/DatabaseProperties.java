package propertyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    static String propertyLocation = (System.getProperty("user.dir") + "\\chapter_002\\src\\main\\resources\\app.properties").replace("\\", File.separator);
    static Properties properties = new Properties();
    private static final String driver = "driver-class-name";
    private static final String url = "url";
    private static final String username = "username";
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

    public static Properties readProperties(String path) {
        try {
            InputStream stream = new FileInputStream(path);
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
        return String.valueOf(properties.get(username));
    }

    public static String password() {
        return String.valueOf(properties.get(password));
    }

    public static String getByKey(String key) {
        return String.valueOf(properties.get("key"));
    }
}
