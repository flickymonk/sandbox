package com.alevel.sandbox.algorithms.dates;

import java.util.Scanner;

public class DayOfYear {

    public static void main(String[] args) {

        System.out.println("January 1 is Monday");
        System.out.println("Please, enter the day of the year:");

        Scanner scanner = new Scanner(System.in);

        int day = scanner.nextInt();

        int counter = 0;
        int month = 1;
        int daysInMonth = 31;

        int dayInYear = day % 365;

        while (counter + daysInMonth < dayInYear) {
            counter += daysInMonth;
            month++;
            if (month == 2) {
                daysInMonth = 28;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }
        }

        int dateInMonth = dayInYear - counter;

        String monthName = null;

        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        int weekDay = day % 7;

        String weekDayName = null;

        switch (weekDay) {
            case 1:
                weekDayName = "Monday";
                break;
            case 2:
                weekDayName = "Tuesday";
                break;
            case 3:
                weekDayName = "Wednesday";
                break;
            case 4:
                weekDayName = "Thursday";
                break;
            case 5:
                weekDayName = "Friday";
                break;
            case 6:
                weekDayName = "Saturday";
                break;
            case 0:
                weekDayName = "Sunday";
                break;
        }

        System.out.println("Your date is " + weekDayName + ", " + monthName + " " + dateInMonth);
    }
}
