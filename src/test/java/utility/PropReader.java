package utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("configuration.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperties(Properties properties) {
        PropReader.properties = properties;
    }
}
