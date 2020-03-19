package com.alevel.sandbox.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

    public static void main(String[] args) {
        var now = new Date();

        System.out.println(now);

        var format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z G");
        System.out.println(format.format(now));
        now.setTime(now.getTime() + 60000);
        System.out.println(now);
    }

}
