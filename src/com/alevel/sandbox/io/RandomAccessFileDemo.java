package com.alevel.sandbox.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) {
        try(RandomAccessFile file = new RandomAccessFile("misc/files/rand.txt", "rw")) {

            System.out.println("First Line Twice");
            long offset = file.getFilePointer();
            System.out.println(file.readLine());
            file.seek(offset);
            System.out.println(file.readLine());

            System.out.println(System.lineSeparator() +
                    "Now, let's write the new line and output the file!");

            long initialLength = file.length();
            file.seek(initialLength);
            String str = System.lineSeparator() + "OMG EOF!";
            file.writeBytes(str);
            file.seek(0);
            String line;
            while ((line = file.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Cleaning up...");
            file.setLength(initialLength);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
