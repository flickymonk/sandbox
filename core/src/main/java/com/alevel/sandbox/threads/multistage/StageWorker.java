package com.alevel.sandbox.threads.multistage;

import java.util.concurrent.ThreadLocalRandom;

public class StageWorker implements Runnable {

    private final Stage assignedStage;

    private final CompletionState completionState;

    public StageWorker(Stage assignedStage, CompletionState completionState) {
        this.assignedStage = assignedStage;
        this.completionState = completionState;
    }

    @Override
    public void run() {
        simulateWork();
        completionState.completeStage(assignedStage);
        System.out.println("Stage " + assignedStage +  " complete");
    }

    private void simulateWork() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
