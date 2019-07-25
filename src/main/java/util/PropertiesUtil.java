package util;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    public static Map getMysqlConfig() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("src\\main\\resources\\application.properties"));
        } catch ( Exception e){
            e.printStackTrace();
        }
        Map mysqlConfig = new HashMap();
        mysqlConfig.put("jdbc.url", p.getProperty("jdbc.url"));
        mysqlConfig.put("jdbc.username", p.getProperty("jdbc.username"));
        mysqlConfig.put("jdbc.password", p.getProperty("jdbc.password"));
        return mysqlConfig;
    }
}
