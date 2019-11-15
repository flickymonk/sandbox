package com.alevel.textboard;

import java.io.IOException;
import java.sql.*;

public class InsertUserRunner {
    public static void main(String[] args) {
        String email = args[0];
        String nickname = args[1];

        System.out.println("Will add user " + nickname + " <" + email + ">");

        try(Connection connection = ConnectionFactory.connect()) {

            String select = "SELECT * FROM users WHERE email = ? OR nickname = ?";

            try(PreparedStatement ps = connection.prepareStatement(select)) {
                ps.setString(1, email);
                ps.setString(2, nickname);

                ResultSet existingUsers = ps.executeQuery();

                if (existingUsers.first()) {
                    System.err.println("Email/Nickname already taken!");
                    return;
                }
            }

            String insert = "INSERT INTO users (email, nickname) VALUES (?, ?)";

            try(PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, email);
                ps.setString(2, nickname);

                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.first()) {
                    long userId = generatedKeys.getLong(1);
                    System.out.println("Inserted user was assigned id = " + userId);
                }

            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
