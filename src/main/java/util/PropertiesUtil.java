package util;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    public static Map getMysqlConfig() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src\\main\\resources\\application.properties"));
        Map mysqlConfig = new HashMap();
        mysqlConfig.put("jdbc.url", p.getProperty("jdbc.url"));
        mysqlConfig.put("jdbc.username", p.getProperty("jdbc.username"));
        mysqlConfig.put("jdbc.password", p.getProperty("jdbc.password"));
        return mysqlConfig;
    }
}
