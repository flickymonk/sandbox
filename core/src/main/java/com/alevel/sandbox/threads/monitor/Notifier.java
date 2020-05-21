package com.alevel.sandbox.threads.monitor;

class Notifier implements Runnable {

    private final Message msg;

    Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (msg) {
            msg.setMessage(name + " work done");
            msg.notifyAll();
        }
    }
}
