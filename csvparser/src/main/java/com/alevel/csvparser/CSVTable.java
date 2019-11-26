package com.alevel.csvparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CSVTable {

    private final Map<String, Integer> header;

    private final List<CSVRow> rows;

    public CSVTable(CSVRow header) {
        int columns = header.size();
        this.header = new HashMap<>(columns);
        for (int i = 0; i < columns; i++) {
            this.header.put(header.get(i).get(), i);
        }
        this.rows = new ArrayList<>();
    }

    public CSVRow getRow(int index) {
        return rows.get(index);
    }

    public List<CSVRow> getRows() {
        return rows;
    }

    public void addRow(CSVRow row) {
        rows.add(row);
    }

    public CSVCell getCell(int row, String column) {
        int index = header.get(column);
        return rows.get(row).get(index);
    }

    public int getColumnIndex(String column) {
        return header.get(column);
    }
}
