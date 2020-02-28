package com.alevel.sandbox.threads.changelistener;

import java.util.Scanner;

public class ChangeTaskDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;

        ChangeListeningTask task = new ChangeListeningTask(
                null,
                value -> System.out.println("New string: " + value)
        );
        new Thread(task).start();

        while (!(input = scanner.nextLine()).equals("quit")) {
            task.update(input);
        }

        task.stop();
    }
}
