package com.alevel.textboard;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionFactory {

    private ConnectionFactory() {
    }

    private static final String DATASOURCE_PROPERTIES_FILE = "/datasource.properties";

    private static Properties datasourceProps;

    public static Connection connect() throws IOException, SQLException {

        if (datasourceProps == null) {
            loadProperties();
        }

        String url = datasourceProps.getProperty("url");

        return DriverManager.getConnection(url, datasourceProps);
    }

    private static void loadProperties() throws IOException {
        datasourceProps = new Properties();
        try (InputStream props = ConnectionFactory.class.getResourceAsStream(DATASOURCE_PROPERTIES_FILE)) {
            datasourceProps.load(props);
        }
    }

}
