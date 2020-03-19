package com.alevel.sandbox.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeAPIDemo {

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(new Date(now.toEpochMilli()));

        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        LocalDateTime dateTime = zonedDateTime.toLocalDateTime();
        System.out.println(dateTime);

        LocalTime time = zonedDateTime.toLocalTime();
        System.out.println(time);

        LocalDate date = zonedDateTime.toLocalDate();
        System.out.println(date);

        System.out.println(zonedDateTime.getMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println(date.format(formatter));

        Duration threeDays = Duration.ofDays(3);
        System.out.println(threeDays);

        ZonedDateTime plusThreeDays = zonedDateTime.plus(threeDays);
        System.out.println(plusThreeDays);
    }

}
