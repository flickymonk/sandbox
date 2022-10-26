package com.alevel.sandbox.threads.multistage;

public class MultiStageDemo {


    public static void main(String[] args) {

        var completionState = new JUCLockCompletionStage();

        for (Stage stage : Stage.values()) {
            new Thread(new StageWorker(stage, completionState)).start();
        }

        try {
            completionState.awaitStage(Stage.A);
            System.out.println("Stage A confirmed");

            completionState.awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All stages are complete");
    }

}
