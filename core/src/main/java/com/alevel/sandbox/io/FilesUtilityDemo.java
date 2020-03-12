package com.alevel.sandbox.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtilityDemo {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("misc/files/sample.txt");

        Files.copy(source, System.out);

        Files.lines(source)
                .flatMapToInt(String::codePoints)
                .filter(Character::isDigit)
                .map(codepoint -> Character.digit(codepoint, 10))
                .reduce((number, next) -> number * 10 + next)
                .ifPresent(number -> System.out.print(System.lineSeparator() + number));
    }
}
