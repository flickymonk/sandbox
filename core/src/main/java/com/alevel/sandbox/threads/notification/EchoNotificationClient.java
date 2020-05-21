package com.alevel.sandbox.threads.notification;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
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

        List<Future<?>> tasks = new ArrayList<>(echoCount);
        for (int i = 0; i < echoCount; i++) {
            Future<?> future = executorService.submit(new NotificationTask(destination, "echo-" + i + ": " + message));
            tasks.add(future);
        }

        for (Future<?> task : tasks) {
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void shutdown() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}
