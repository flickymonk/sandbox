package com.alevel.csvparser;

import java.util.Objects;

public final class CSVCell {

    private final String value;

    public CSVCell(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public int getInt() {
        return Integer.parseInt(value);
    }

    public long getLong() {
        return Long.parseLong(value);
    }

    public double getDouble() {
        return Double.parseDouble(value);
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSVCell)) return false;
        CSVCell csvCell = (CSVCell) o;
        return Objects.equals(value, csvCell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "CSVCell{" +
                "value='" + value + '\'' +
                '}';
    }
}
