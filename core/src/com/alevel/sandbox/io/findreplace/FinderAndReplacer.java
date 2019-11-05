package com.alevel.sandbox.io.findreplace;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinderAndReplacer {

    private final File file;

    public FinderAndReplacer(File file) {
        this.file = file;
    }

    public void find(String str) throws IOException {
        Pattern pattern = Pattern.compile(str);
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));

        try (br) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                boolean foundSubstring = matcher.find();
                if (foundSubstring) {
                    sb.append(line);
                }
            }
        }

        System.out.println(sb);
    }

    public void replace(String str1, String str2) throws IOException {
        Pattern pattern = Pattern.compile(str1);
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));

        try (br) {
            String line;
            while ((line = br.readLine()) != null) {
                if (sb.length() != 0) {
                    sb.append(System.lineSeparator());
                }
                Matcher matcher = pattern.matcher(line);
                boolean foundSubstring = matcher.find();
                if (foundSubstring) {
                    String modified = matcher.replaceAll(str2);
                    System.out.println(modified);
                    sb.append(modified);
                } else {
                    sb.append(line);
                }
            }
        }

        Writer writer = new FileWriter(file);

        try (writer) {
            writer.write(sb.toString());
        }

    }

}
