package com.alevel.sandbox.io;

import java.io.*;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteToFile {
    public static void main(String[] args) {
        //generate 10 lines of random text
        String randomText = Stream.generate(UUID::randomUUID)
                .limit(10)
                .map(UUID::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(randomText);

        //write to file
        try(Writer writer = new BufferedWriter(new FileWriter("misc/files/rand.txt"))) {
            writer.write(randomText);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
