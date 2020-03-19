package com.alevel.sandbox.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDemo {

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        Date time = calendar.getTime();
        System.out.println(time);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));

//        calendar.setLenient(false);
//        calendar.set(Calendar.DAY_OF_MONTH, 213);

        calendar.add(Calendar.DAY_OF_MONTH, 30);
        System.out.println(calendar.getTime());

        calendar.roll(Calendar.DAY_OF_MONTH, 3);
        System.out.println(calendar.getTime());
        calendar.roll(Calendar.DAY_OF_MONTH, 15);
        System.out.println(calendar.getTime());
    }

}
