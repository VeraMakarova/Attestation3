package ru.inno.attestation3.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUrl() {
        String url = properties.getProperty("baseUrl");
        return url;
    }

    public static String getLoginPageUrl() {
        String url = properties.getProperty("loginPageURL");
        return url;
    }

    public static String getBooksPageUrl() {
        String url = properties.getProperty("booksPageURL");
        return url;
    }
    public static String getProfilePageUrl() {
        String url = properties.getProperty("profilePageURL");
        return url;
    }
    public static String getUserName() {
        String userName = properties.getProperty("userName");
        return userName;
    }
    public static String getPassword() {
        String password = properties.getProperty("password");
        return password;
    }

    public static String getUserId() {
        String userId = properties.getProperty("userId");
        return userId;
    }
}
