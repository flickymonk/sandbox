package com.alevel.sandbox.io.findreplace;

import java.io.File;
import java.io.IOException;

public class FindReplace {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Path to file expected in arguments");
        }
        String path = args[0];
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + path);
        }

        FinderAndReplacer fr = new FinderAndReplacer(file);
        if (args.length < 2) {
            throw new IllegalArgumentException("At least 2 arguments expected: no string to find or replace!");
        }
        String str1 = args[1];
        if (args.length > 2) {
            String str2 = args[2];
            fr.replace(str1, str2);
        } else {
            fr.find(str1);
        }

    }

}
