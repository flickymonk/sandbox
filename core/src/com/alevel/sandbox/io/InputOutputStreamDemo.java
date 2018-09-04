package com.alevel.sandbox.io;

import java.io.*;

public class InputOutputStreamDemo {
    public static void main(String[] args) {

        try(InputStream in = new FileInputStream("misc/files/sample.txt");
            OutputStream out = new FileOutputStream("misc/files/sample_copy.txt")) {

            byte[] buffer = new byte[4096];
            while (in.available() > 0) {
                int read = in.read(buffer);
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
