package com.alevel.datewizard.model;

import java.util.Objects;

public final class DateOfYear {

    private final int dayOfMonth;

    private final Month month;

    private final DayOfWeek dayOfWeek;

    public DateOfYear(int dayOfMonth, Month month, DayOfWeek dayOfWeek) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateOfYear that = (DateOfYear) o;
        return dayOfMonth == that.dayOfMonth &&
                month == that.month &&
                dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfMonth, month, dayOfWeek);
    }

    @Override
    public String toString() {
        return "DateOfYear{" +
                "dayOfMonth=" + dayOfMonth +
                ", month=" + month +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
