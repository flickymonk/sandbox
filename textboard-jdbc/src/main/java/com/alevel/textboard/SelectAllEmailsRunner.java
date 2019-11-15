package com.alevel.textboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAllEmailsRunner {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.connect()) {

            try(Statement findAllEmails = connection.createStatement()) {

                ResultSet emails = findAllEmails.executeQuery("SELECT email FROM users;");

                while (emails.next()) {
                    System.out.println(emails.getString("email"));
                }

            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
