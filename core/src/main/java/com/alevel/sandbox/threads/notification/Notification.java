package com.alevel.sandbox.threads.notification;

import java.util.concurrent.*;

public class Notification {

    public static void main(String[] args) throws InterruptedException {
        final int echoCount = 10;

        System.out.println("---------------------- SINGLE THREAD POOL ----------------------");
        System.out.print("Enter message:\n>");

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        NotificationClient notificationClient1 = new EchoNotificationClient(singleThreadExecutor, System.in, echoCount);
        notificationClient1.send(System.out);
        notificationClient1.shutdown();

        System.out.println("---------------------- CACHED THREAD POOL ----------------------");
        System.out.print("Enter message:\n>");

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        NotificationClient notificationClient2 = new EchoNotificationClient(cachedThreadPool, System.in, echoCount);
        notificationClient2.send(System.out);
        notificationClient2.shutdown();

        System.out.println("---------------------- FIXED THREAD POOL ----------------------");
        System.out.print("Enter message:\n>");

        ExecutorService fixedThreadPool  = Executors.newFixedThreadPool(echoCount / 2);
        NotificationClient notificationClient3 = new EchoNotificationClient(fixedThreadPool, System.in, echoCount);
        notificationClient3.send(System.out);
        notificationClient3.shutdown();
    }

}
