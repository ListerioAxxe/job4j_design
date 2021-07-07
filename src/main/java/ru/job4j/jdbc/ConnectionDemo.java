package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        URL path = ClassLoader.getSystemResource("ap.properties");
        var findArg = new FindArg(path.getPath());
        findArg.find();
        try (Connection connection = DriverManager
                .getConnection(findArg.get("url"), findArg.get("login"), findArg.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private static class FindArg {
        private static final Logger LOG = LoggerFactory.getLogger(FindArg.class.getName());
        private final String path;
        private final Map<String, String> valuesOf;

        public FindArg(String path) {
            this.path = path;
            valuesOf = new HashMap<>();
        }

        private void find() {
            try (var reader = new BufferedReader(new FileReader(path))) {
                reader.lines()
                        .forEach(x -> {
                            String[] s = x.split("=");
                            if (s.length == 2) {
                                valuesOf.put(s[0], s[1]);
                            }
                        });
            } catch (Exception e) {
                LOG.error("Invalid file", e);
            }
        }

        public String get(String key) {
           return valuesOf.get(key);
        }
    }
}

