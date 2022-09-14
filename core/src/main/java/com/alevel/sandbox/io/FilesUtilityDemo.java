package com.alevel.sandbox.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesUtilityDemo {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("misc/files/sample.txt");

        Files.copy(source, System.out);

        Stream<String> lines = Files.lines(source);
        try (lines) {
            lines
                    .flatMapToInt(String::codePoints)
                    .filter(Character::isDigit)
                    .map(codepoint -> Character.digit(codepoint, 10))
                    .asLongStream()
                    .reduce((number, next) -> number * 10 + next)
                    .ifPresent(number -> System.out.printf("%n%d%n", number));
        }
    }
}
