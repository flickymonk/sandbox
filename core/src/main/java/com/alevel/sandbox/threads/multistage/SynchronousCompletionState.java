package com.alevel.sandbox.threads.multistage;

import java.util.EnumSet;
import java.util.Set;

public class SynchronousCompletionState implements CompletionState {

    private final Set<Stage> incomplete = EnumSet.allOf(Stage.class);

    @Override
    public synchronized void completeStage(Stage stage) {
        if (incomplete.remove(stage) && incomplete.isEmpty()) {
            this.notifyAll();
        }
    }

    @Override
    public synchronized void awaitCompletion() throws InterruptedException {
        if (!incomplete.isEmpty()) {
            this.wait();
        }
    }
}
