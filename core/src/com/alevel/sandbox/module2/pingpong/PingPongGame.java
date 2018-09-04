package com.alevel.sandbox.module2.pingpong;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingPongGame {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        GameState gameState = new GameState();
        Scanner scanner = new Scanner(System.in);

        Opponent opponent = new Opponent(gameState);
        Player player = new Player(gameState, scanner);
        executorService.execute(player);
        executorService.execute(opponent);

        try {
            gameState.awaitGameOver();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

        executorService.shutdownNow();

        System.exit(0);
    }

}
