package propertyreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    static String propertyLocation = (System.getProperty("user.dir") + "\\src\\main\\resources\\app.properties").replace("\\", File.separator);
    static Properties properties = new Properties();
    private static final String DRIVER = "driver-class-name";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

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
        return String.valueOf(properties.get(DRIVER));
    }

    public static String url() {
        return String.valueOf(properties.get(URL));
    }

    public static String login() {
        return String.valueOf(properties.get(USERNAME));
    }

    public static String password() {
        return String.valueOf(properties.get(PASSWORD));
    }

    public static String getByKey(String key) {
        return String.valueOf(properties.get("key"));
    }
}
