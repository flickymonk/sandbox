package com.alevel.csvparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public final class CSVFileParser implements CSVParser<Path> {

    @Override
    public CSVTable parse(Path source) throws CSVParsingException {
        try(BufferedReader reader = Files.newBufferedReader(source)) {
            String header = reader.readLine();
            if (header == null) {
                throw new CSVParsingException("Can't parse CSV, file is empty");
            }
            CSVTable table = new CSVTable(parseLine(header));
            String line;
            while ((line = reader.readLine()) != null) {
                table.addRow(parseLine(line));
            }
            return table;
        } catch (IOException e) {
            throw new CSVParsingException(e);
        }
    }

    private CSVRow parseLine(String line) {
        return new CSVRow(Arrays.asList(line.split(",")));
    }
}
