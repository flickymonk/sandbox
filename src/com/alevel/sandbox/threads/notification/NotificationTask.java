package com.alevel.sandbox.threads.notification;

import java.io.PrintStream;

class NotificationTask implements Runnable {

    private final PrintStream destination;

    private final String message;

    NotificationTask(PrintStream destination, String message) {
        this.destination = destination;
        this.message = message;
    }

    @Override
    public void run() {
        destination.println(message);
    }
}
