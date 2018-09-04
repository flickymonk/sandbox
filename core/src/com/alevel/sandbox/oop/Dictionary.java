package com.alevel.sandbox.oop;

import java.util.Objects;

public class Dictionary {

    private final String language;

    public Dictionary(String language) {
        this.language = language;
    }

    public class Word {

        private final String language = Dictionary.this.language;

        private final String word;

        private final String definition;

        public Word(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }

        public String getLanguage() {
            return language;
        }

        public String getWord() {
            return word;
        }

        public String getDefinition() {
            return definition;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word1 = (Word) o;
            return Objects.equals(language, word1.language) &&
                    Objects.equals(word, word1.word) &&
                    Objects.equals(definition, word1.definition);
        }

        @Override
        public int hashCode() {

            return Objects.hash(language, word, definition);
        }

        @Override
        public String toString() {
            return "Word{" +
                    "language='" + language + '\'' +
                    ", word='" + word + '\'' +
                    ", definition='" + definition + '\'' +
                    '}';
        }
    }

    public Word defineWord(String word, String definition) {
        return new Word(word, definition);
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary that = (Dictionary) o;
        return Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(language);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "language='" + language + '\'' +
                '}';
    }
}
