package oop.composition;

import java.time.DayOfWeek;
import java.time.Month;

public class MyDate {

    private int year;
    private int month;
    private Month months;
    private DayOfWeek dayOfWeek;
    private int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public boolean isValidDate(int year, int month, int day) {
        if (year >= 1900 && year <= 2100) {
            if (month >= 1 && month <= 12) {
                if (day == dayOfMonth[month]) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getDayOfWeek(int year, int month, int day){
        MyDate date = new MyDate(1900, 1, 1, DayOfWeek.MONDAY);
        MyDate second = new MyDate(year, month, day);

        return date.dayOfWeek.getValue();
    }

    public MyDate(int year, int month, int day, DayOfWeek dayOfWeek) {
        this.year = year;
        this.month = month;
        this.months = months;
        this.dayOfWeek = dayOfWeek;
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = day;
    }

    public MyDate() {
    }
}