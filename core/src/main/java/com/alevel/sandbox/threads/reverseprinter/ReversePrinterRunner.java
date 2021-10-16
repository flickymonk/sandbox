package com.alevel.sandbox.threads.reverseprinter;

import java.util.ArrayList;
import java.util.Collections;

public class ReversePrinterRunner {

    private static final int SIZE = 50;

    public static void main(String[] args) {

        ReverseOrderPrintNameTask last = null;

        var tasks = new ArrayList<ReverseOrderPrintNameTask>(SIZE);
        var barrier = new Barrier(SIZE);
        for (int i = 0; i < SIZE; i++) {
            var task = new ReverseOrderPrintNameTask(i, barrier, last);
            tasks.add(task);
            last = task;
        }

        Collections.shuffle(tasks);

        tasks.forEach(task -> new Thread(task).start());
    }
}
