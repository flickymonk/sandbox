package com.alevel.csvparser;

public interface CSVParser<T> {
    CSVTable parse(T source) throws CSVParsingException;
}
