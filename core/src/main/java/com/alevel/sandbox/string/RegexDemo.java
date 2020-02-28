package com.alevel.sandbox.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    private static final String GMAIL_REGEX = "..*@gmail\\.com";

    private static final Pattern GMAIL_PATTERN = Pattern.compile(GMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        String target = "test@gmail.com";

        System.out.println(target.matches(GMAIL_REGEX));

        Matcher matcher = GMAIL_PATTERN.matcher(target);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println(matcher.matches());

        String result = matcher.replaceAll("final result");
        System.out.println(result);
    }
}
