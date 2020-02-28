package com.alevel.sandbox.generics;

public class UpperBoundGenericDemo {

    public static void main(String[] args) {

        Message msg = new Mail("Hi there, how are you?");
        EmptyMessageFilter<Message> messageFilter = new EmptyMessageFilter<>();
        if (messageFilter.filter(msg)) {
            msg.send("61000");
        }

    }

    interface Message {
        String getContent();
        boolean send(String address);
    }

    private static class Mail implements Message {
        private final String content;

        private Mail(String content) {
            this.content = content;
        }

        @Override
        public String getContent() {
            return content;
        }

        @Override
        public boolean send(String address) {
            System.out.println("Message [" + content +"] sent to " + address);
            return true;
        }
    }

    private static class EmptyMessageFilter<T extends Message> {

        boolean filter(T msg) {
            return !msg.getContent().isEmpty();
        }

    }

}
