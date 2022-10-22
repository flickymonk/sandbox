package com.alevel.sandbox.io;

import java.io.*;

public class SerializationDemo {

    public static void main(String[] args) {

        File destination = createFile("misc/files/serial");

        Box box = new Box(10, 10, 10, "m1", "m2", "m3");
        System.out.println(box);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destination))) {
            out.writeObject(box);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(destination))) {
            Object o = in.readObject();
            if (o instanceof Box) {
                Box deserializedBox = (Box) o;
                System.out.println(deserializedBox);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static File createFile(String path) {
        File destination = new File(path);

        try {
            if (!destination.createNewFile()) {
                System.err.println("File already exists");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return destination;
    }

}
