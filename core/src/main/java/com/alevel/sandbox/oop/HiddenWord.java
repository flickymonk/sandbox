package com.alevel.sandbox.oop;

public class HiddenWord {

    private static final char DEFAULT_PLACEHOLDER = '*';

    private final String placeholder;

    public HiddenWord(String word) {
        this(word, DEFAULT_PLACEHOLDER);
    }

    public HiddenWord(String word, char placeholder) {
        int wordLength = word.length();
        this.placeholder = String.valueOf(placeholder).repeat(wordLength);
    }

    public String display() {
        return placeholder;
    }

}
