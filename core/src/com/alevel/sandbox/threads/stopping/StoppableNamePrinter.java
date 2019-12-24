package com.alevel.sandbox.threads.stopping;

public class StoppableNamePrinter implements Runnable, Stoppable {

    private boolean running = true;

    @Override
    public void stop() {
        System.out.println("Stopping execution");
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Execution stopped");
    }
}
