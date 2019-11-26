package com.alevel.csvparser;

import java.util.ArrayList;
import java.util.List;

public final class CSVRow {

    private final List<CSVCell> cells;

    public CSVRow() {
        cells = new ArrayList<>();
    }

    public CSVRow(List<String> cells) {
        this.cells = new ArrayList<>(cells.size());
        for (String cell : cells) {
            this.cells.add(new CSVCell(cell));
        }
    }

    public void append(String value) {
        cells.add(new CSVCell(value));
    }

    public int size() {
        return cells.size();
    }

    public boolean isEmpty() {
        return cells.isEmpty();
    }

    public List<CSVCell> asList() {
        return new ArrayList<>(cells);
    }

    public CSVCell get(int index) {
        return cells.get(index);
    }

}
