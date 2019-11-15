package com.alevel.textboard;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String DATASOURCE_PROPERTIES_FILE = "/datasource.properties";

    private static Properties datasourceProps = new Properties();

    public static Connection connect() throws IOException, SQLException {

        if (datasourceProps.isEmpty()) {
            loadProperties();
        }

        String url = datasourceProps.getProperty("url");

        return DriverManager.getConnection(url, datasourceProps);
    }

    private static void loadProperties() throws IOException {
        try (InputStream props = SelectAllEmailsRunner.class.getResourceAsStream(DATASOURCE_PROPERTIES_FILE)) {
            datasourceProps.load(props);
        }
    }

}
