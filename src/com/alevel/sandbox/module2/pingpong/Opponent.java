package com.alevel.sandbox.module2.pingpong;

final class Opponent implements Runnable {

    private static final String EMPTY_STRING = "";

    private static final int TURN_LENGTH = 2000;


    private final GameState gameState;

    Opponent(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        while (!gameState.isOver()) {
            System.out.println("Bamn!");
            try {
                Thread.sleep(TURN_LENGTH);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String input = this.gameState.getInput().getAndSet(EMPTY_STRING);
            if (input == null || input.isEmpty()) {
                gameState.finishGame();
            }
        }
        System.out.println("You've lost!");
    }
}
