package com.alevel.sandbox.io;

import java.io.*;

public class ReadFromFile {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("misc/files/sample.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
