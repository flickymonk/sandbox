package com.alevel.sandbox.io.tree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class FileTree {

    public static void main(String[] args) {
        String baseDir;
        if (args.length == 0) {
            System.out.println("Input the name of the directory to display:");
            baseDir = new Scanner(System.in).nextLine();
        } else  {
            baseDir = args[0];
        }

        Path basePath = Paths.get(baseDir);
        if (Files.notExists(basePath)) {
            exitOnError("Directory '" + baseDir + "' not found!");
        } else if (!Files.isDirectory(basePath)) {
            exitOnError('\'' + baseDir + "' is not a directory!");
        }

        try {
            System.out.println(traverseDirectory(basePath));
        } catch (IOException e) {
            exitOnError(e.getLocalizedMessage());
        }
    }

    private static void exitOnError(String message) {
        System.err.println(message);
        throw new RuntimeException(message);
    }

    private static final String BRANCH = "+--- ";

    private static final String INDENT = ".    ";

    private static String traverseDirectory(Path basePath) throws IOException {
        StringBuilder outputBuilder = new StringBuilder();

        Files.walkFileTree(basePath, new SimpleFileVisitor<>() {

            private int level = -1;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.preVisitDirectory(dir, attrs);
                appendFile(dir, ++level);
                return result;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);
                appendFile(file, level + 1);
                return result;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                level--;
                return super.postVisitDirectory(dir, exc);
            }

            private void appendFile(Path path, int level) {
                if (level > 0) {
                    outputBuilder
                            .append(INDENT.repeat(level - 1))
                            .append(BRANCH);
                }
                outputBuilder
                        .append(path.getFileName())
                        .append(System.lineSeparator());
            }

        });

        return outputBuilder.toString();
    }

}