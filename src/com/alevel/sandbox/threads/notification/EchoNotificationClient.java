package com.alevel.sandbox.threads.notification;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class EchoNotificationClient implements NotificationClient {

    private final Scanner scanner;

    private final ExecutorService executorService;

    private final int echoCount;

    EchoNotificationClient(ExecutorService executorService, InputStream source, int echoCount) {
        this.scanner = new Scanner(source);
        this.echoCount = echoCount;
        this.executorService = executorService;
    }

    @Override
    public void send(PrintStream destination) {
        String message = scanner.nextLine();

        for (int i = 0; i < echoCount; i++) {
            executorService.execute(new NotificationTask(destination, "echo-" + i + ": " + message));
        }
    }

    @Override
    public void shutdown() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}
