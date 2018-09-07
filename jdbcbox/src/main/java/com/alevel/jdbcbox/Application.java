package com.alevel.jdbcbox;

import com.alevel.jdbcbox.common.SingleConnectionPool;
import com.alevel.jdbcbox.common.StorageException;
import com.alevel.jdbcbox.player.Player;
import com.alevel.jdbcbox.player.PlayerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Supplier;

public final class Application {
    public static void main(String[] args) {
        Properties connectionProps = new Properties();

        try (InputStream props = Application.class.getResourceAsStream("/datasource.properties")) {
            connectionProps.load(props);
        } catch (IOException e) {
            panic(e);
        }

        String url = connectionProps.getProperty("url");
        try (Connection connection = DriverManager.getConnection(url, connectionProps)) {

            Supplier<Connection> connectionSupplier = new SingleConnectionPool(connection);

            //create and save players
            PlayerRepository playerRepository = new PlayerRepository(connectionSupplier);
            for (String playerName : inputPlayerNames()) {
                playerRepository.save(new Player(playerName));
            }
            for (Player player : playerRepository.list()) {
                System.out.println(player);
            }
        } catch (SQLException | StorageException e) {
            panic(e);
        }
    }

    private static List<String> inputPlayerNames() {
        System.out.println("Input player names:");
        Scanner scanner = new Scanner(System.in);
        List<String> names = new LinkedList<>();
        String name;
        while (!(name = scanner.nextLine()).isEmpty()) {
            names.add(name);
        }
        return names;
    }

    private static void panic(Throwable e) {
        e.printStackTrace();
        System.exit(1);
    }
}
