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
            System.out.println("Input player names:");
            Scanner scanner = new Scanner(System.in);
            List<String> names = new LinkedList<>();
            String name;
            while (!(name = scanner.nextLine()).isEmpty()) {
                names.add(name);
            }
            for (String playerName : names) {
                playerRepository.save(new Player(playerName));
            }

            System.out.println("Input player id:");
            long id = scanner.nextLong();
            System.out.printf("%2s | %10s | %10s | %10s\n" +
                            "-----------------------------------------\n",
                    "id", "name", "rank", "score");
            printPlayer(playerRepository.get(id));
            System.out.print("\n\nAll Players:\n");

            for (Player player : playerRepository.list()) {
                printPlayer(player);
            }
        } catch (SQLException | StorageException e) {
            panic(e);
        }
    }

    private static void printPlayer(Player player) {
        System.out.printf("%2d | %10s | %10s | %10d\n",
                player.getId(), player.getNickname(), player.getRank(), player.getScore());
    }

    private static void panic(Throwable e) {
        e.printStackTrace();
        System.exit(1);
    }
}
