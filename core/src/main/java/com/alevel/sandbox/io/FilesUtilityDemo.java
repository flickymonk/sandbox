package com.alevel.sandbox.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesUtilityDemo {
    public static void main(String[] args) throws IOException {
        Files.copy(Paths.get("misc/files/sample.txt"), System.out);
    }
}
