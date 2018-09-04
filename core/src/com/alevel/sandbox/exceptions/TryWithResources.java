package com.alevel.sandbox.exceptions;

import java.io.*;

public class TryWithResources {

    public static void main(String[] args) {

        try (InputStream is = new FileInputStream("misc/files/sample.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File sample.txt not found!");
        } catch (IOException e) {
            System.err.println("Error on file read!");
            e.printStackTrace();
        }

    }

}
