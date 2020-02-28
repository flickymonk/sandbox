package com.alevel.sandbox.module2.pingpong;

import java.util.Scanner;

final class Player implements Runnable {

    private final GameState gameState;

    private final Scanner scanner;

    Player(GameState gameState, Scanner scanner) {
        this.gameState = gameState;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (!gameState.isOver()) {
            gameState.getInput().set(scanner.nextLine());
        }
    }

}
