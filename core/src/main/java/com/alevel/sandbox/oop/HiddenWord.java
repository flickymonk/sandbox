package com.alevel.sandbox.oop;

public class HiddenWord {

    private static final char DEFAULT_PLACEHOLDER = '*';

    private final String word;

    private final char placeholder;

    public HiddenWord(String word) {
        this(word, DEFAULT_PLACEHOLDER);
    }

    public HiddenWord(String word, char placeholder) {
        this.placeholder = placeholder;
        this.word = word;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            sb.append(placeholder);
        }
        return sb.toString();
    }

}
