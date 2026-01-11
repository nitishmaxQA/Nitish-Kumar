package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configreader {
    static String filepath = "src/main/resources/GlobalData/test.properties";
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {

        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return properties.getProperty("base_url");
    }

    public static String postPutEndpoint() {
        return properties.getProperty("postPutEndpoint");
    }

    public static String getDeleteEndpoint() {
        return properties.getProperty("getDeleteEndpoint");
    }

    public static String Environment() {
        return properties.getProperty("Environment");
    }

    public static String username() {
        return properties.getProperty("username");
    }

    public static String jsonFilePath() {
        return properties.getProperty("jsonFilePath");
    }


}
