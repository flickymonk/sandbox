package com.alevel.sandbox.threads.nameprinter;

public class NamePrinter {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " RUNNING");

        for (int i = 0; i < 10; i++) {
            new NamePrinterTask("Thread " + i).start();
        }

    }
}
