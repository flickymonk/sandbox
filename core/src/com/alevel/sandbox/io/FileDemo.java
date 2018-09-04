package com.alevel.sandbox.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

public class FileDemo {

    public static void main(String[] args) {
        File file = new File("misc/files/sample.txt");
        System.out.println("File: " + file.getAbsolutePath());
        System.out.println("Does this file exist? " + file.exists());
        System.out.println("Is this a directory? " + file.isDirectory());
        System.out.println("Can I read from it? " + file.canRead());

        File folder = new File("misc/files/folder");
        boolean created = folder.mkdir();
        if(created) {
            System.out.println("Folder created: " + folder.getPath());
        } else {
            System.out.println("Folder already existed");
        }

        File commonFolder = new File("misc/files");
        String[] textFiles = commonFolder.list(new TextFileFilter());
        Objects.requireNonNull(textFiles, "Folder unreachable!");
        System.out.println("Text files in folder " + commonFolder.getPath());
        for (String textFile : textFiles) {
            System.out.println(textFile);
        }
    }

    private static final class TextFileFilter implements FilenameFilter {
        @Override
        public boolean accept(File file, String name) {
            return name.toLowerCase().endsWith(".txt");
        }
    }

}
