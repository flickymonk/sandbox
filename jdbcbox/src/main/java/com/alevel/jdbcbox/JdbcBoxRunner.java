package com.alevel.jdbcbox;

import com.alevel.jdbcbox.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.Properties;

public class JdbcBoxRunner {

    private static final Logger log = LoggerFactory.getLogger(JdbcBoxRunner.class);

    public static void main(String[] args) {

        Properties props = loadProperties();

        String url = props.getProperty("url");

        log.info("Connecting to {}", url);

        try(Connection connection = DriverManager.getConnection(url, props)) {

            try(Statement getTime = connection.createStatement()) {
                ResultSet resultSet = getTime.executeQuery("SELECT current_timestamp;");
                if (resultSet.next()) {
                    Timestamp timestamp = resultSet.getTimestamp(1);
                    log.info("Database timestamp {}", timestamp);
                }
            }

            try(PreparedStatement getActiveNumbersAndNamesLike = connection.prepareStatement(
                    "SELECT phone, name FROM phone_book WHERE active = true AND phone LIKE ?")) {

                getActiveNumbersAndNamesLike.setString(1, "380%");

                ResultSet resultSet = getActiveNumbersAndNamesLike.executeQuery();

                while (resultSet.next()) {

                    String phone = resultSet.getString("phone");
                    String name = resultSet.getString("name");

                    log.info("Active contact with matching number found. Phone: {}, name: {}", phone, name);
                }
            }

            Contact[] contacts = {
                    new Contact("10438530324", "John Doe", null),
                    new Contact("10438530325", "Jane Doe", null),
                    new Contact("10438530326", "Carl Doe", "Son of John and Jane Doe")
            };

            try(PreparedStatement insertContact = connection.prepareStatement(
                    "INSERT INTO phone_book (phone, name, description) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;",
                    PreparedStatement.RETURN_GENERATED_KEYS
            )) {

                for (Contact contact : contacts) {
                    insertContact.setString(1, contact.getPhoneNumber());
                    insertContact.setString(2, contact.getName());
                    insertContact.setString(3, contact.getDescription());

                    insertContact.addBatch();
                }

                insertContact.executeBatch();

                ResultSet generatedKeys = insertContact.getGeneratedKeys();

                while (generatedKeys.next()) {
                    log.info("inserted new contact. ID : {}", generatedKeys.getLong("id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() {

        Properties props = new Properties();

        try(InputStream input = JdbcBoxRunner.class.getResourceAsStream("JdbcBox.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return props;
    }

}
