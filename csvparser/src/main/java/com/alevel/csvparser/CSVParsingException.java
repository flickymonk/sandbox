package com.alevel.csvparser;

public class CSVParsingException extends Exception {
    public CSVParsingException(String message) {
        super(message);
    }

    public CSVParsingException(Throwable cause) {
        super(cause);
    }
}
