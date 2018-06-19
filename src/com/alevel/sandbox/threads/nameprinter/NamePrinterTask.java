package com.alevel.sandbox.threads.nameprinter;

class NamePrinterTask extends Thread {

    NamePrinterTask(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " RUNNING");
    }

}
