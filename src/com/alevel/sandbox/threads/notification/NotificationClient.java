package com.alevel.sandbox.threads.notification;

import java.io.PrintStream;

interface NotificationClient {

    void send(PrintStream destination);

    void shutdown() throws InterruptedException;

}
