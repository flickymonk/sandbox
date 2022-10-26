package com.alevel.sandbox.threads.multistage;

public interface CompletionState {

    void completeStage(Stage stage);

    void awaitCompletion() throws InterruptedException;


}
