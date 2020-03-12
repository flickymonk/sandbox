package com.alevel.sandbox.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ReadFromFile {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("misc/files/sample.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            System.out.print(sb);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
